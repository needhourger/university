/*
 * @Description:
 * @Author: cc
 * @Date: 2021-04-25 10:56:11
 * @LastEditors: cc
 * @LastEditTime: 2021-04-25 12:52:29
 */
#include "Shop.h"

Shop::Shop(int num_barbers, int num_chairs) {

    this->num_barbers = num_barbers > 0 ? num_barbers : kDefaultBarbers;
    this->num_chairs = num_chairs > 0 ? num_chairs : kDefaultNumChairs;

    pthread_mutex_init(&mutex, NULL);

    for (int i = 0;i < num_barbers;i++) {
        barbers[i] = Barber(i);
        pthread_cond_init(&barbers[i].barber_cond, NULL);
    }

}


Shop::Shop() {
    num_barbers = kDefaultBarbers;
    num_chairs = kDefaultNumChairs;

    pthread_mutex_init(&mutex, NULL);

    for (int i = 0;i < kDefaultBarbers;i++) {
        barbers[i] = Barber(i);
        pthread_cond_init(&barbers[i].barber_cond, NULL);
    }

}


int Shop::visitShop(int customer_id) {

    pthread_mutex_lock(&mutex);

    if (waiting_customers.size() == num_chairs && sleeping_barbers.empty()) {
        printf("customer[%d]: leaves the shop because of no available waiting chairs. \n", customer_id);
        num_drops++;
        pthread_mutex_unlock(&mutex);
        return -1;
    }

    customers[customer_id] = Customer(customer_id);
    pthread_cond_init(&customers[customer_id].customer_cond, NULL);
    int barber_id;

    if (sleeping_barbers.empty()) {
        waiting_customers.push(customer_id);

        printf("customer[%d]: takes a waiting chair. # waiting seats available = %d \n", customer_id, num_chairs - waiting_customers.size());
        while (customers[customer_id].my_barber == -1) {
            pthread_cond_wait(&customers[customer_id].customer_cond, &mutex);
        }
        barber_id = customers[customer_id].my_barber;

    }
    else {
        barber_id = sleeping_barbers.front();
        sleeping_barbers.pop();
        customers[customer_id].my_barber = barber_id;
        barbers[barber_id].my_customer = customer_id;
    }

    printf("customer[%d]: moves to a service chair[%d], # waiting seats available = %d \n", customer_id, barber_id, num_chairs - waiting_customers.size());

    customers[customer_id].state = SERVICE;
    pthread_cond_signal(&barbers[barber_id].barber_cond);
    pthread_mutex_unlock(&mutex);

    return barber_id;
}


void Shop::leaveShop(int customer_id, int barber_id) {

    pthread_mutex_lock(&mutex);
    printf("customer[%d]: wait for barber[%d] to be done with hair-cut\n", customer_id, barber_id);

    while (customers[customer_id].my_barber != -1) {
        pthread_cond_wait(&customers[customer_id].customer_cond, &mutex);
    }

    printf("customer[%d]: says good-bye to barber[%d]\n", customer_id, barber_id);

    customers[customer_id].state = LEAVING;
    pthread_cond_signal(&barbers[barber_id].barber_cond);

    pthread_mutex_unlock(&mutex);

}


void Shop::helloCustomer(int barber_id) {

    pthread_mutex_lock(&mutex);

    if (barbers[barber_id].my_customer == -1) {
        printf("barber  [%d]: sleeps because of no customers.\n", barber_id);
        sleeping_barbers.push(barber_id);
        while (barbers[barber_id].my_customer == -1) {
            pthread_cond_wait(&barbers[barber_id].barber_cond, &mutex);
        }
    }

    while (customers[barbers[barber_id].my_customer].state != SERVICE) {
        pthread_cond_wait(&barbers[barber_id].barber_cond, &mutex);
    }

    printf("barber  [%d]: starts a hair-cut service for customer[%d]\n", barber_id, barbers[barber_id].my_customer);

    pthread_mutex_unlock(&mutex);

}


void Shop::byeCustomer(int barber_id) {

    pthread_mutex_lock(&mutex);
    printf("barber  [%d]: says he's done with a hair-cut service for customer[%d]\n", barber_id, barbers[barber_id].my_customer);

    customers[barbers[barber_id].my_customer].my_barber = -1;
    pthread_cond_signal(&customers[barbers[barber_id].my_customer].customer_cond);
    while (customers[barbers[barber_id].my_customer].state != LEAVING) {
        pthread_cond_wait(&barbers[barber_id].barber_cond, &mutex);
    }
    barbers[barber_id].my_customer = -1;

    printf("barber  [%d]: calls in another customer.\n", barber_id);
    if (!waiting_customers.empty()) {
        int customer_id = waiting_customers.front();
        waiting_customers.pop();
        barbers[barber_id].my_customer = customer_id;
        customers[customer_id].my_barber = barber_id;
        pthread_cond_signal(&customers[customer_id].customer_cond);
    }

    pthread_mutex_unlock(&mutex);

}