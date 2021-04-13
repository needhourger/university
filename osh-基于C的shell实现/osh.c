/*
 * @Description:
 * @Author: cc
 * @Date: 2021-04-13 09:35:03
 * @LastEditors: cc
 * @LastEditTime: 2021-04-13 12:11:19
 */
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<stdbool.h>
#include<sys/wait.h>
#include<sys/types.h>
#include<unistd.h>
#include<fcntl.h>

#define BUFF_SIZE 1024
#define ARGS_NUM 64
#define LINE_DELIM " \r\t\n\a"

char* line = nullptr;
char* pline = nullptr;
bool ampersand = false;


char* readCommandLine() {
    char* line = nullptr;
    size_t buffsize = 0;
    if (getline(&line, &buffsize, stdin) == -1) {
        if (!feof(stdin)) {
            perror("readline");
            exit(-1);
        }
    }
    fflush(stdin);
    return line;
}


void addToHistory(char* line) {
    if (strcmp(line, "!!\n") != 0) {
        if (pline) free(pline);
        pline = (char*)malloc(sizeof(line));
        if (!pline) {
            perror("pline");
            exit(-1);
        }
        memcpy(pline, line, sizeof(line));
    }
}


char** splitCommandLine(char* line, int* argc) {
    int argsNum = ARGS_NUM;
    char** args = nullptr;
    char* arg = nullptr;
    int p = 0;

    args = (char**)malloc(argsNum * sizeof(char*));
    if (!args) {
        perror("args");
        exit(-1);
    }

    arg = strtok(line, LINE_DELIM);
    while (arg) {
        args[p] = arg;
        p++;

        if (p >= argsNum) {
            argsNum += ARGS_NUM;
            args = (char**)realloc(args, argsNum * sizeof(char*));
            if (!args) {
                perror("args");
                exit(-1);
            }
        }

        arg = strtok(NULL, LINE_DELIM);
    }

    args[p] = NULL;
    *argc = p;
    return args;
}


void catchSignal(int signal) {
    pid_t pid;
    while ((pid = waitpid(-1, NULL, WNOHANG)) > 0);
}


int callCommandWithRedi(char** args, int left, int right) {
    int inNum = 0;
    int outNum = 0;
    char* inFile = nullptr;
    char* outFile = nullptr;
    int end = right;

    for (int i = left;i < right;i++) {
        if (strcmp(args[i], "<") == 0) {
            ++inNum;
            if (i + 1 < right)
                inFile = args[i + 1];
            else return -1;

            if (end == right) end = i;
        }
        else if (strcmp(args[i], ">") == 0) {
            ++outNum;
            if (i + 1 < right)
                outFile = args[i + 1];
            else return -1;

            if (end == right) end = i;
        }
    }

    if (inNum == 1) {
        FILE* fp = fopen(inFile, "r");
        if (!fp) return -1;
        fclose(fp);
    }

    if (inNum > 1) {
        return -1;
    }
    else if (outNum > 1) {
        return -1;
    }

    int result = 0;
    pid_t pid = fork();
    if (pid < 0) {
        result = -1;
    }
    else if (pid == 0) {
        if (inNum == 1) freopen(inFile, "r", stdin);
        if (outNum == 1) freopen(outFile, "w", stdout);

        char* command[ARGS_NUM];
        for (int i = left;i < end;i++) command[i] = args[i];
        command[end] = NULL;
        execvp(command[left], command + left);
        exit(-1);
    }
    else {
        int status;
        int err = 0;
        if (ampersand) {
            signal(SIGCHLD, catchSignal);
        }
        else {
            waitpid(pid, &status, 0);
            err = WEXITSTATUS(status);
        }

        if (err) {
            printf("%s", strerror(err));
        }
    }
    return result;
}


int callCommandWithPipe(char** args, int left, int right) {
    if (left >= right) return 0;

    int pipep = -1;
    for (int i = left;i < right;i++) {
        if (strcmp(args[i], "|") == 0) {
            pipep = i;
            break;
        }
    }
    if (pipep == -1) {
        return callCommandWithRedi(args, left, right);
    }
    else if (pipep + 1 == right) {
        return -1;
    }

    int fds[2];
    if (pipe(fds) == -1) {
        return -1;
    }
    int result = 0;
    pid_t pid = fork();
    if (pid < 0) {
        return -1;
    }
    else if (pid == 0) {
        close(fds[0]);
        dup2(fds[1], STDOUT_FILENO);
        close(fds[1]);

        result = callCommandWithPipe(args, left, pipep);
        exit(result);
    }
    else {
        int status;
        int exitCode;
        if (ampersand) {
            signal(SIGCHLD, catchSignal);
            exitCode = 0;
        }
        else {
            waitpid(pid, &status, 0);
            exitCode = WEXITSTATUS(status);
        }

        if (exitCode != 0) {
            char info[4096] = { 0 };
            char line[BUFF_SIZE];
            close(fds[1]);
            dup2(fds[0], STDIN_FILENO);
            close(fds[0]);
            while (fgets(line, BUFF_SIZE, stdin) != NULL) {
                strcat(info, line);
            }
            printf("%s", info);

            result = exitCode;
        }
        else {
            close(fds[1]);
            dup2(fds[0], STDIN_FILENO);
            close(fds[0]);
            result = callCommandWithPipe(args, pipep + 1, right);
        }
    }
    return result;
}


void analysisArgs(char** args, int argc) {

    if (argc < 0) {
        return;
    }

    if (argc == 1 && strcmp(args[0], "exit") == 0) {
        exit(0);
    }

    if (argc == 1 && strcmp(args[0], "!!") == 0) {
        if (pline) {
            if (line) free(line);
            line = (char*)malloc(sizeof(pline));
            if (!line) {
                perror(line);
                exit(-1);
            }
            memcpy(line, pline, sizeof(pline));

            int argc = 0;
            if (args) free(args);
            args = splitCommandLine(line, &argc);
            analysisArgs(args, argc);
            return;
        }
        else {
            printf("No command history\n");
            return;
        }
    }

    if (argc > 1 && strcmp(args[argc - 1], "&") == 0) {
        ampersand = true;
        argc--;
        args[argc] = NULL;
    }

    pid_t pid = fork();
    if (pid < 0) {
        perror("fork");
        exit(-1);
    }
    else if (pid == 0) {
        int infs = dup(STDIN_FILENO);
        int outfs = dup(STDOUT_FILENO);

        int result = callCommandWithPipe(args, 0, argc);

        dup2(infs, STDIN_FILENO);
        dup2(outfs, STDOUT_FILENO);
        exit(result);

    }
    else {
        int status;
        if (ampersand) {
            signal(SIGCHLD, catchSignal);
        }
        else {
            waitpid(pid, &status, 0);
        }
        free(line);
        free(args);
    }
}


int main() {
    char** args = nullptr;
    int argc = 0;

    while (1) {
        printf("osh> ");
        fflush(stdout);

        line = readCommandLine();
        addToHistory(line);
        args = splitCommandLine(line, &argc);
        analysisArgs(args, argc);
    }


    return 0;
}