#ifndef RITKAVEKTOR

#define RITKAVEKTOR

#include <cmath>

template <class T>
struct nonZero
{
	T elem;
	int index;
};


template<class T>
class RitkaVektor
{
	nonZero <T>* vektor;
	int nZeroDim;
	int dim;

public:

	RitkaVektor();
	RitkaVektor(int dim);
	RitkaVektor(T *t, int dim);
	RitkaVektor(const RitkaVektor&);
    ~RitkaVektor();

	void SetSize(int);
	T GetValue(int) const;
	RitkaVektor operator=(const RitkaVektor&);
	RitkaVektor operator+(const RitkaVektor&);
	RitkaVektor operator-(const RitkaVektor&);
	double operator*(const RitkaVektor&);
	double operator~();
	double operator%(const RitkaVektor&);
	T      operator[](int) const;
	operator Vektor<T>();
};

// Alapertelmezett konstruktor
template<class T>
RitkaVektor<T>::RitkaVektor()
{
	vektor = new nonZero<T>{};
	nZeroDim = 0;
	dim = 0;
}

// Konstruktor
template<class T>
RitkaVektor<T>::RitkaVektor(int dim)
{
	vektor = new nonZero<T>[1];
	vektor[0].index = 0;
	vektor[0].elem = 1;
	nZeroDim = 1;
	this->dim = dim;
}

// Masolo konstruktor
template<class T>
RitkaVektor<T>::RitkaVektor(T* t, int dim)
{
	int nZero = 0;
	for (int i = 0; i < dim; ++i)
		if (t[i])
			++nZero;
	vektor = new nonZero<T>[nZero];
	nZeroDim = nZero;
	this->dim = dim;
	int index = 0;
	for (int i = 0; i < dim; ++i)
	{
		if (t[i])
		{
			vektor[index].elem = t[i];
			vektor[index].index = i;
			++index;
		}
	}
}

// Masolo konstruktor
template<class T>
RitkaVektor<T>::RitkaVektor(const RitkaVektor& t)
{
	this->vektor = new nonZero<T>[t.nZeroDim];
	this->nZeroDim = t.nZeroDim;
	this->dim = t.dim;
	for (int i = 0; i < t.nZeroDim; ++i)
	{
		this->vektor[i] = t.vektor[i];
	}
}

// Destrukor
template<class T>
RitkaVektor<T>::~RitkaVektor()
{
	delete[] vektor;
}

// Uj meret beallitasa
template<class T>
void RitkaVektor<T>::SetSize(int size)
{
	delete[] this->vektor;
	this->vektor = new nonZero<T>[size];
	this->nZeroDim = size;
}

// Segedfuggveny a [] operatorhoz
template<class T>
T RitkaVektor<T>::GetValue(const int i) const
{
	if (i >= this->dim || i < 0)
		throw("Hiba: Out of range!");
	int j = 0;
	while (j < this->nZeroDim && this->vektor[j].index != i)
		++j;
	return j < this->nZeroDim ? this->vektor[j].elem : 0;
}

// Egyenloseg operator
template<class T>
RitkaVektor<T> RitkaVektor<T>::operator=(const RitkaVektor& t)
{
	if (this->dim != t.dim)
		throw("Hiba: Kulonbozo dimenzio!");
	delete[] this->vektor;
	this->vektor = new nonZero<T>[t.nZeroDim];
	this->nZeroDim = t.nZeroDim;
	for (int i = 0; i < t.nZeroDim; ++i)
	{
		this->vektor[i] = t.vektor[i];
	}
	return *this;
}

// Osszeadas
template<class T>
RitkaVektor<T> RitkaVektor<T>::operator+(const RitkaVektor& t)
{
	if (this->dim != t.dim)
		throw("Hiba: Kulonbozo dimenzio!");

	RitkaVektor<T> tmp(this->dim);
	RitkaVektor<T> *e = new RitkaVektor(this->dim);
	tmp.SetSize(this->dim);

	int i = 0, j = 0, db = 0, s;

	for (i = 0; i < this->dim; ++i)
	{
		s = this->GetValue(i) + t.GetValue(i);
		if (s)
		{
			tmp.vektor[db].elem = s;
			tmp.vektor[db].index = i;
			++db;
		}
	}

	if (db)
	{
		e->SetSize(db);
		for (i = 0; i < db; ++i)
		{
			e->vektor[i] = tmp.vektor[i];
		}
	}
	else
	{
		e->nZeroDim = 0;
	}
	return *e;
}

// Kivonas
template<class T>
RitkaVektor<T> RitkaVektor<T>::operator-(const RitkaVektor& t)
{
	if (this->dim != t.dim)
		throw("Hiba: Kulonbozo dimenzio!");

	RitkaVektor<T> tmp(this->dim);
	RitkaVektor<T> *e = new RitkaVektor(this->dim);
	tmp.SetSize(this->dim);

	int i = 0, j = 0, db = 0, s;

	for (i = 0; i < this->dim; ++i)
	{
		s = this->GetValue(i) - t.GetValue(i);
		if (s)
		{
			tmp.vektor[db].elem = s;
			tmp.vektor[db].index = i;
			++db;
		}
	}

	if (db)
	{
		e->SetSize(db);
		for (i = 0; i < db; ++i)
		{
			e->vektor[i] = tmp.vektor[i];
		}
	}
	else
	{
		e->nZeroDim = 0;
	}
	return *e;
}

// Skalaris szorzat
template<class T>
double RitkaVektor<T>::operator*(const RitkaVektor& t)
{
	if (this->dim != t.dim)
		throw("Hiba: Kulonbozo dimenzio!");
	double s = 0;
	for (int i = 0; i < this->dim; ++i)
		s += this->GetValue(i) * t.GetValue(i);
	return  s;
}

// Norma
template<class T>
double RitkaVektor<T>::operator~()
{
	double norma = 0;
	for (int i = 0; i < nZeroDim; ++i)
		norma += (this->vektor[i].elem * this->vektor[i].elem);
	return sqrt(norma);
}

// Euklideszi tavolsag
template <class T>
double RitkaVektor<T>::operator%(const RitkaVektor& t)
{
	if (this->dim != t.dim)
		throw("Hiba: Kulonbozo dimenzio!");
	double d = 0;
	for (int i = 0; i < this->dim; ++i)
	{
		d += (t.GetValue(i) - this->GetValue(i))* (t.GetValue(i) - this->GetValue(i));
	}
	return  sqrt(d);
}

// Elem lekerdezes
template<class T>
T RitkaVektor<T>::operator[](int i) const
{
	return this->GetValue(i);
}

// Konverzio
template<class T>
RitkaVektor<T>::operator Vektor<T>()
{
	T* a = new T[this->dim];
	for (int i = 0; i < this->dim; ++i)
	{
		a[i] = this->GetValue(i);
	}
	Vektor<T> v(a, this->dim);
	delete[] a;
	return v;
}

#endif