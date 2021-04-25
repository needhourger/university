/*
 * @Description:
 * @Author: cc
 * @Date: 2021-04-24 11:11:21
 * @LastEditors: cc
 * @LastEditTime: 2021-04-25 12:47:47
 */
#ifndef SHOP_H
#define SHOP_H
#include<pthread.h>
#include<iostream>
#include<sstream>
#include<string>
#include<queue>
#include<map>
using namespace std;

#define kDefaultNumChairs 3
#define kDefaultBarbers 1

enum CustomerState { WAIT, SERVICE, LEAVING };

class Barber {
public:
    Barber() {};
    Barber(int id) :id(id) {};
    int id;
    pthread_cond_t barber_cond;
    int my_customer = -1;
};

class Customer {
public:
    Customer() {};
    Customer(int id) :id(id) {};
    int id;
    pthread_cond_t customer_cond;
    CustomerState state = WAIT;
    int my_barber = -1;
};

class Shop {
public:
    Shop(int num_barbers, int num_chairs);
    Shop();

    int visitShop(int id);
    void leaveShop(int customer_id, int barber_id);
    void helloCustomer(int id);
    void byeCustomer(int id);
    int getCustomerDrops() { return num_drops; };

private:
    int num_barbers;
    int num_chairs;

    pthread_mutex_t mutex;

    map<int, Customer> customers;
    map<int, Barber> barbers;

    queue<int>waiting_customers;
    queue<int>sleeping_barbers;

    int num_drops = 0;

};

#endif