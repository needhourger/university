// --------------------------- Program 3: C++ Standard I/O Library --------------------------------
// Helen Hu
// CSS 503B
// Creation Date: 5/15/2021
// Date of Last Modification: 5/25/2021
//
// Purpose:
// In this program, I will design and implement my own core input and output functions of the C/C++ 
// standard I/O library: stdio.h. 

// ------------------------------ stdio.cpp -------------------------------------------------------
#include <fcntl.h>
#include <sys/types.h>
#include <sys/uio.h>
#include <unistd.h>
#include <strings.h>
#include <string.h>
#include <stdarg.h>   
#include <stdlib.h>    
using namespace std;

char decimal[100];// set decimal array size

// this method turns an integer type data into char string type and stores it in the decimal[]
int recursive_itoa(int arg)
{
	int div = arg / 10;
	int mod = arg % 10;
	int index = 0;
	if (div > 0)
	{
		index = recursive_itoa(div);
	}
	decimal[index] = mod + '0';
	return ++index;
}

// this is the function method for printf
char* itoa(const int arg)
{
	bzero(decimal, 100);// initializes all the data in decimal[] to be 0 
	int order = recursive_itoa(arg);
	char* new_decimal = new char[order + 1];
	bcopy(decimal, new_decimal, order + 1);
	return new_decimal;
}

// this method prints formatted output to stdout
int printf(const void* format, ...)
{
	va_list list;
	va_start(list, format);

	char* msg = (char*)format;
	char buf[1024];
	int nWritten = 0; // initializes the number of characters printed

	int i = 0, j = 0, k = 0;
	// when we have not reached EOF, keep printing
	while (msg[i] != '\0')
	{
		if (msg[i] == '%' && msg[i + 1] == 'd')
		{
			buf[j] = '\0';
			nWritten += write(1, buf, j);
			j = 0;
			i += 2;

			int int_val = va_arg(list, int);
			char* dec = itoa(abs(int_val));
			if (int_val < 0)
			{
				nWritten += write(1, "-", 1);
			}
			nWritten += write(1, dec, strlen(dec));
			delete dec;
		}
		else
		{
			buf[j++] = msg[i++];
		}
	}
	if (j > 0)
	{
		nWritten += write(1, buf, j);
	}
	va_end(list);
	return nWritten;// return the number of character we have printed
}

// Add a user-defined buffer and set the buffering mode.  
// LINE_BUFFER mode is not supported in this program
int setvbuf(FILE* stream, char* buf, int mode, size_t size)
{
	if (mode != _IONBF && mode != _IOLBF && mode != _IOFBF)
	{
		return -1;
	}
	stream->mode = mode;
	stream->pos = 0;
	if (stream->buffer != (char*)0 && stream->bufown == true)
	{
		delete stream->buffer;
	}

	switch (mode)
	{
	case _IONBF:
		stream->buffer = (char*)0;
		stream->size = 0;
		stream->bufown = false;
		break;
	case _IOLBF:
	case _IOFBF:
		if (buf != (char*)0)
		{
			stream->buffer = buf;
			stream->size = size;
			stream->bufown = false;
		}
		else
		{
			stream->buffer = new char[BUFSIZ];
			stream->size = BUFSIZ;
			stream->bufown = true;
		}
		break;
	}
	return 0;
}

// this method sets the size of an input/output stream buffer
void setbuf(FILE* stream, char* buf)
{
	setvbuf(stream, buf, (buf != (char*)0) ? _IOFBF : _IONBF, BUFSIZ);
}

FILE* fopen(const char* path, const char* mode)
{
	FILE* stream = new FILE();
	setvbuf(stream, (char*)0, _IOFBF, BUFSIZ);

	// fopen( ) mode
	// r or rb = O_RDONLY
	// w or wb = O_WRONLY | O_CREAT | O_TRUNC
	// a or ab = O_WRONLY | O_CREAT | O_APPEND
	// r+ or rb+ or r+b = O_RDWR
	// w+ or wb+ or w+b = O_RDWR | O_CREAT | O_TRUNC
	// a+ or ab+ or a+b = O_RDWR | O_CREAT | O_APPEND

	switch (mode[0])
	{
	case 'r':
		if (mode[1] == '\0')            // r
		{
			stream->flag = O_RDONLY;
		}
		else if (mode[1] == 'b')
		{
			if (mode[2] == '\0')          // rb
			{
				stream->flag = O_RDONLY;
			}
			else if (mode[2] == '+')      // rb+
			{
				stream->flag = O_RDWR;
			}
		}
		else if (mode[1] == '+')        // r+  r+b
		{
			stream->flag = O_RDWR;
		}
		break;
	case 'w':
		if (mode[1] == '\0')            // w
		{
			stream->flag = O_WRONLY | O_CREAT | O_TRUNC;
		}
		else if (mode[1] == 'b')
		{
			if (mode[2] == '\0')          // wb
			{
				stream->flag = O_WRONLY | O_CREAT | O_TRUNC;
			}
			else if (mode[2] == '+')      // wb+
			{
				stream->flag = O_RDWR | O_CREAT | O_TRUNC;
			}
		}
		else if (mode[1] == '+')        // w+  w+b
		{
			stream->flag = O_RDWR | O_CREAT | O_TRUNC;
		}
		break;
	case 'a':
		if (mode[1] == '\0')            // a
		{
			stream->flag = O_WRONLY | O_CREAT | O_APPEND;
		}
		else if (mode[1] == 'b')
		{
			if (mode[2] == '\0')          // ab
			{
				stream->flag = O_WRONLY | O_CREAT | O_APPEND;
			}
			else if (mode[2] == '+')      // ab+
			{
				stream->flag = O_RDWR | O_CREAT | O_APPEND;
			}
		}
		else if (mode[1] == '+')        // a+  a+b
		{
			stream->flag = O_RDWR | O_CREAT | O_APPEND;
		}
		break;
	}

	mode_t open_mode = S_IRUSR | S_IWUSR | S_IRGRP | S_IWGRP | S_IROTH | S_IWOTH;

	if ((stream->fd = open(path, stream->flag, open_mode)) == -1)
	{
		delete stream;
		printf("fopen failed\n");
		stream = NULL;
	}

	return stream;
}

// fpurge: like fflush, but without writing anything: leave the
// given FILE's buffer empty.

int fpurge(FILE* stream)
{
	memset(stream->buffer, 0, stream->size);// empty the stream buffer
	stream->actual_size = 0;// set the actual size to be 0
	stream->pos = 0;// set the position to be 0
	return 0;// return 0
}

// this method flushes the unused bytes inside the buffer 
int fflush(FILE* stream)
{
	// if (stream->flag == O_WRONLY) {
	// 	write(stream->fd, stream->buffer + stream->pos, stream->actual_size - stream->pos);
	// }
	memset(stream->buffer, 0, stream->size);
	stream->pos = 0; // set the position to be 0
	stream->actual_size = 0; // set the actual size to be 0
	return 0;// return 0
}

// use memcpy to fill the buffer when the read is smaller than the buffer size;
// read the full buffer size of data when filling the buffer;
// when the read is bigger than the buffer size, avoid double buffering, 
// read the data directly from stream to the client buffer.
// the total number of elements successfully read is returned.
// nmemb = number items of data with each size bytes long

size_t fread(void* ptr, size_t size, size_t nmemb, FILE* stream)
{
	// keep tracks of the number of bytes we have to print out
	size_t bytes_required = size * nmemb;
	memset(ptr, 0, bytes_required);// set the ptr to be 0
	if (stream->lastop == 'w') {
		if (stream->actual_size > 0) {
			write(stream->fd, stream->buffer, stream->actual_size);
		}
		fflush(stream);
	}
	stream->lastop = 'r';

	size_t s0 = 0; // initializes s0

  // check if it is non buffer mode first
  // check if there is data and if the buffer can be fit it
	if (stream->mode != _IONBF && stream->pos < stream->actual_size) {
		if (bytes_required <= stream->actual_size - stream->pos) {
			memcpy(ptr, stream->buffer + stream->pos, bytes_required);// copy all the data from the buffer
			stream->pos += bytes_required;// update the positition
			return bytes_required;// return the number of bytes we read
		}

		// if we need bigger size than the buffer
		else {
			s0 = stream->actual_size - stream->pos;// records how many data we have read 
			memcpy(ptr, stream->buffer + stream->pos, s0);// copy all the data from the buffer
			stream->pos = stream->actual_size;// update the positition
			bytes_required -= s0;// read the remainder
		}
	}

	size_t n_blocks = bytes_required / stream->size; // how many full blocks we need to read 
	size_t remaining = bytes_required % stream->size;// how many remainder we need to read

	size_t s1 = 0;// initialize the size we need to read
	if (n_blocks > 0) {
		s1 = read(stream->fd, ptr + s0, n_blocks * stream->size);
		if (s1 < n_blocks * stream->size) {
			stream->eof = true; // reaches the end of file
			return s0 + s1;// return the number of bytes we read
		}
	}


	if (stream->mode != _IONBF) {
		// read the full size of the buffer
		stream->actual_size = read(stream->fd, stream->buffer, stream->size);
		stream->pos = 0;// initialize the position
		if (stream->pos < stream->actual_size) {
			// check if the remaining is smaller than the actual size
			if (remaining <= stream->actual_size) {
				memcpy(ptr + s0 + s1, stream->buffer, remaining);
				stream->pos += remaining;
				return s0 + s1 + remaining;// return the number of bytes we read
			}


			else {
				memcpy(ptr + s0 + s1, stream->buffer, stream->actual_size);
				stream->pos = stream->actual_size;
				return s0 + s1 + stream->actual_size;
			}
		}
		else {
			stream->eof = true;// reach the end of the file
		}
	}
	// when we read directly from the stream to client buffer
	else {
		size_t s2 = read(stream->fd, ptr + s0 + s1, remaining);
		return s0 + s1 + s2;
	}

	return s0 + s1;
}

size_t fwrite(const void* ptr, size_t size, size_t nmemb, FILE* stream)
{
	// comlete it
	if (stream->lastop == 'r') {
		if (stream->pos < stream->actual_size) {
			lseek(stream->fd, -(stream->actual_size - stream->pos), SEEK_CUR);
		}
		fflush(stream);
	}
	stream->lastop = 'w';

	size_t bytes_written = size * nmemb;
	if (stream->actual_size + bytes_written <= stream->size) {
		memcpy(stream->buffer + stream->actual_size, ptr, bytes_written);
		stream->actual_size += bytes_written;
		return bytes_written;
	}

	memcpy(stream->buffer + stream->actual_size, ptr, stream->size - stream->actual_size);
	write(stream->fd, stream->buffer, stream->size);
	stream->actual_size = 0;
	bytes_written = bytes_written - (stream->size - stream->actual_size);
	size_t s0 = stream->size - stream->actual_size;

	size_t n_blocks = bytes_written / stream->size;
	size_t remaining = bytes_written % stream->size;
	size_t s1 = 0;
	if (n_blocks > 0) {
		write(stream->fd, ptr + s0, n_blocks * stream->size);
		s1 = n_blocks * stream->size;
	}
	memcpy(stream->buffer, ptr + s0 + s1, remaining);
	stream->actual_size = remaining;
	return s0 + s1 + remaining;
	// return 	write(stream->fd, ptr, bytes_written);
}

int fgetc(FILE* stream)
{
	// complete it
	if (stream->lastop == 'w') {
		if (stream->actual_size > 0) {
			write(stream->fd, stream->buffer, stream->actual_size);
		}
		fflush(stream);
	}
	stream->lastop = 'r';

	int ret = -1;
	char c;
	if (stream->mode != _IONBF) {
		if (stream->pos < stream->actual_size) {
			c = stream->buffer[stream->pos++];
			ret = c;
		}
		else {
			stream->actual_size = read(stream->fd, stream->buffer, stream->size);
			stream->pos = 0;
			if (stream->pos < stream->actual_size) {
				c = stream->buffer[stream->pos++];
				ret = c;
			}
			else {
				stream->eof = true;
			}
		}
	}
	else {
		size_t s = read(stream->fd, &c, 1);
		ret = s == 1 ? c : -1;
	}
	return ret;
}

int fputc(int c, FILE* stream)
{
	// complete it
	if (stream->lastop == 'r') {
		if (stream->pos < stream->actual_size) {
			lseek(stream->fd, -(stream->actual_size - stream->pos), SEEK_CUR);
		}
		fflush(stream);
	}
	stream->lastop = 'w';

	if (stream->mode != _IONBF) {
		if (stream->actual_size < stream->size) {
			stream->buffer[stream->actual_size++] = c;
		}
		else {
			write(stream->fd, stream->buffer, stream->size);
			stream->actual_size = 0;
			stream->buffer[stream->actual_size++] = c;
		}
	}
	else {
		write(stream->fd, &c, 1);
	}
	return 0;
}

char* fgets(char* str, int size, FILE* stream)
{
	// complete it
	if (stream->lastop == 'w') {
		if (stream->actual_size > 0) {
			write(stream->fd, stream->buffer, stream->actual_size);
		}
		fflush(stream);
	}
	stream->lastop = 'r';

	memset(str, 0, size);
	size_t s = read(stream->fd, str, size - 1);
	if (s == 0) return nullptr;

	int p = 0;
	while (p < s && str[p] != '\n') p++;
	if (p == s) return str;
	else {
		str[p + 1] = '\0';
		lseek(stream->fd, -s + p + 1, SEEK_CUR);
	}

	return str;
}

int fputs(const char* str, FILE* stream)
{
	// complete it
	return fwrite(str, strlen(str), 1, stream);
}

int feof(FILE* stream)
{
	return stream->eof == true;
}

int fseek(FILE* stream, long offset, int whence)
{
	// complete it
	if (stream->lastop == 'w') {
		if (stream->actual_size > 0) {
			write(stream->fd, stream->buffer, stream->actual_size);
		}
		fflush(stream);
	}
	lseek(stream->fd, offset, whence);
	return 0;
}

int fclose(FILE* stream)
{
	// complete it
	if (stream->lastop == 'w' && stream->actual_size > 0) {
		write(stream->fd, stream->buffer, stream->actual_size);
	}
	if (stream->buffer) {
		delete stream->buffer;
	}
	delete stream;
	close(stream->fd);
	return 0;
}
