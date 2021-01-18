#include "Poly.h"
#include <cstring>
using namespace std;

ostream &operator<<(ostream &output, const Poly &p)
{
    for (int i = p.getSize() - 1; i >= 0; i--)
    {
        if (p.getCoeff(i) != 0)
        {
            cout << " ";
            if (p.getCoeff(i) > 0)
                output << "+";
            output << p.getCoeff(i);
            if (i > 0)
            {
                if (i == 1)
                    output << "x";
                else
                    output << "x^" << i;
            }
        }
    }

    return output;
}

istream &operator>>(istream &input, Poly &p)
{
    int coeff, power;
    while (input)
    {
        input >> coeff >> power;
        if (coeff == -1 && power == -1)
            return input;
        p.setCoeff(coeff, power);
    }

    return input;
}

Poly::Poly(int coeff, int power)
{
    if (power < 0)
        return;

    size = power + 1;
    coeffPtr = new int[size];
    memset(coeffPtr, 0, sizeof(int) * size);

    coeffPtr[power] = coeff;
}

Poly::Poly(int coeff)
{
    size = 1;
    coeffPtr = new int[size];
    coeffPtr[0] = coeff;
}

Poly::Poly()
{
    size = 1;
    coeffPtr = new int[size];
    coeffPtr[0] = 0;
}

Poly::Poly(const Poly &p)
{
    size = p.getSize();
    coeffPtr = new int[size];
    for (int i = 0; i < size; i++)
    {
        coeffPtr[i] = p.getCoeff(i);
    }
}

Poly::~Poly()
{
    delete coeffPtr;
    coeffPtr = NULL;
}

Poly Poly::operator+(const Poly &p)
{
    int s = size > p.getSize() ? size : p.getSize();
    Poly ret;

    for (int i = s - 1; i >= 0; i--)
        ret.setCoeff(coeffPtr[i] + p.getCoeff(i), i);

    return ret;
}

Poly Poly::operator-(const Poly &p)
{
    int s = size > p.getSize() ? size : p.getSize();
    Poly ret;

    for (int i = s - 1; i >= 0; i--)
        ret.setCoeff(this->getCoeff(i) - p.getCoeff(i), i);
    return ret;
}

Poly Poly::operator*(const Poly &p)
{
    Poly ret;

    for (int i = size - 1; i >= 0; i--)
    {
        for (int j = p.getSize() - 1; j >= 0; j--)
        {
            int t = ret.getCoeff(i + j);
            t += this->getCoeff(i) * p.getCoeff(j);
            ret.setCoeff(t, i + j);
        }
    }

    return ret;
}

Poly &Poly::operator=(const Poly &p)
{
    if (this == &p)
        return *this;

    size = p.getSize();
    delete coeffPtr;
    coeffPtr = new int[size];
    for (int i = 0; i < size; i++)
        coeffPtr[i] = p.getCoeff(i);
    return *this;
}

Poly &Poly::operator+=(const Poly &p)
{
    int s = size > p.getSize() ? size : p.getSize();
    for (int i = 0; i < s; i++)
        this->setCoeff(this->getCoeff(i) + p.getCoeff(i), i);
    return *this;
}

Poly &Poly::operator-=(const Poly &p)
{
    int s = size > p.getSize() ? size : p.getSize();
    for (int i = 0; i < s; i++)
        this->setCoeff(this->getCoeff(i) - p.getCoeff(i), i);
    return *this;
}

Poly &Poly::operator*=(const Poly &p)
{
    Poly ret;

    for (int i = size - 1; i >= 0; i--)
    {
        for (int j = p.getSize() - 1; j >= 0; j--)
        {
            int t = ret.getCoeff(i + j);
            t += this->getCoeff(i) * p.getCoeff(j);
            ret.setCoeff(t, i + j);
        }
    }
    *this = ret;
    return *this;
}

bool Poly::operator==(const Poly &p) const
{
    if (size != p.getSize())
        return false;
    for (int i = 0; i < size; i++)
        if (coeffPtr[i] != p.getCoeff(i))
            return false;
    return true;
}

bool Poly::operator!=(const Poly &p) const
{
    if (*this == p)
        return false;
    return true;
}

int Poly::getCoeff(int coefficient) const
{
    if (coefficient >= 0 && coefficient < size)
        return coeffPtr[coefficient];
    return 0;
}

void Poly::setCoeff(int coeff, int power)
{
    if (power >= size)
    {
        int *p = new int[power + 1];
        memset(p, 0, sizeof(int) * (power + 1));
        memcpy(p, coeffPtr, size * sizeof(int));
        p[power] = coeff;
        size = power + 1;
        delete coeffPtr;
        coeffPtr = p;
    }
    else
    {
        coeffPtr[power] = coeff;
    }
}

int Poly::getSize() const
{
    return size;
}