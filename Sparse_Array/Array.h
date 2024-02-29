#ifndef VEKTOR

#define VEKTOR

#include <cmath>

template<class T>
class RitkaVektor;

template<class T>
class Vektor
{
	T* m_t;
	int m_dim;

public:

	Vektor();
	Vektor(int dim);
	Vektor(T *t, int dim);
	Vektor(const Vektor&);
    ~Vektor();

	Vektor& operator+(const Vektor&);
	Vektor operator-(const Vektor&);
	Vektor operator=(const Vektor&);
	double operator*(const Vektor&);
	double operator~();
	double operator%(const Vektor&);
	T      operator[](int) const;
	operator RitkaVektor<T>();
};

// Alapertelmezett konstruktor
template<class T>
Vektor<T>::Vektor()
{
	m_t = new T{};
	m_dim = 0;
}

// Konstruktor
template<class T>
Vektor<T>::Vektor(int dim)
{
	m_t = new T[dim];
	m_dim = dim;
	for (int i = 0; i < m_dim; ++i)
		m_t[i] = 1;
}

// Masolo konstruktor
template<class T>
Vektor<T>::Vektor(T* t, int dim)
{
	m_t = new T[dim];
	m_dim = dim;
	for (int i = 0; i < dim; ++i)
		m_t[i] = t[i];
}

// Masolo konstruktor
template<class T>
Vektor<T>::Vektor(const Vektor& t)
{
	m_dim = t.m_dim;
	m_t = new T[m_dim];
	for (int i = 0; i < m_dim; ++i)
		m_t[i] = t.m_t[i];
}

// Destruktor
template<class T>
Vektor<T>::~Vektor()
{
		delete m_t;
}

// Osszeadas
template<class T>
Vektor<T>& Vektor<T>::operator+(const Vektor& t)
{
	if (this->m_dim != t.m_dim)
		throw("Hiba: Kulonbozo dimenzio!");
	Vektor<T> *tmp = new Vektor<T>(*this);
	for (int i = 0; i < m_dim; ++i)
		tmp->m_t[i] += t.m_t[i];
	return  *tmp;
}

// Kivonas
template<class T>
Vektor<T> Vektor<T>::operator-(const Vektor& t)
{
	if (this->m_dim != t.m_dim)
		throw("Hiba: Kulonbozo dimenzio!");
	Vektor<T> *tmp = new Vektor<T>(*this);
	for (int i = 0; i < m_dim; ++i)
		tmp->m_t[i] -= t.m_t[i];
	return *tmp;
}

// Egyenloseg operator
template<class T>
Vektor<T> Vektor<T>::operator=(const Vektor& t)
{
	delete this->m_t;
	m_dim = t.m_dim;
	m_t = new T[m_dim];
	for (int i = 0; i < m_dim; ++i)
		m_t[i] = t.m_t[i];
	return *this;
}

// Skalaris szorzat
template<class T>
double Vektor<T>::operator*(const Vektor& t)
{
	if (this->m_dim != t.m_dim)
		throw("Hiba: Kulonbozo dimenzio!");
	double s = 0;
	for (int i = 0; i < m_dim; ++i)
		s += this->m_t[i] * t.m_t[i];
	return  s;
}

// Vektor norma
template<class T>
double Vektor<T>::operator~()
{
	double norma = 0;
	for (int i = 0; i < m_dim; ++i)
		norma += m_t[i] * m_t[i];
	return sqrt(norma);
}

// Euklideszi tavolsag
template <class T>
double Vektor<T>::operator%(const Vektor& t)
{
	if (this->m_dim != t.m_dim)
		throw("Hiba: Kulonbozo dimenzio!");
	double d = 0;
	for (int i = 0; i < m_dim; ++i)
	{
		d += (t.m_t[i] - this->m_t[i]) * (t.m_t[i] - this->m_t[i]);
	}
	return sqrt(d);
}

// Elem lekerdezes
template<class T>
T Vektor<T>::operator[](int i) const
{
	if (i >= this->m_dim || i < 0)
		throw("Hiba: Out of range!");
	return this->m_t[i];
}

// Konverzio
template<class T>
Vektor<T>::operator RitkaVektor<T>()
{
	return RitkaVektor<T>(this->m_t, m_dim);
}
#endif
