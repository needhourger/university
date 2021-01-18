#ifndef POLY_H
#define POLY_H
#include <iostream>
using namespace std;

class Poly
{
    friend ostream &operator<<(ostream &output, const Poly &p);
    friend istream &operator>>(istream &input, Poly &p);

public:
    Poly(int coeff, int power);
    Poly(int coeff);
    Poly();
    Poly(const Poly &p);

    ~Poly();

    Poly operator+(const Poly &p);
    Poly operator-(const Poly &p);
    Poly operator*(const Poly &p);

    Poly& operator=(const Poly &p);
    Poly& operator+=(const Poly &p);
    Poly& operator-=(const Poly &p);
    Poly& operator*=(const Poly &p);

    bool operator==(const Poly &p) const;
    bool operator!=(const Poly &p) const;

    int getCoeff(int coefficient) const;

    void setCoeff(int coeff, int power);

    int getSize() const;

private:
    int size;
    int *coeffPtr;
};

#endif