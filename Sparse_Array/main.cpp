#include <iostream>
#include "Vektor.h"
#include "RitkaVektor.h"

using namespace std;

int main()
{
	try {
		// Vektor tezt
		cout << "Vektor muveletek:\n";
		double t[5] = { 1.6, 2.14, 0, 12.456, 0 };

		Vektor<double>v1(t, 5);

		cout << "v1: ";
		for (int i = 0; i < 5; ++i)
		{
			cout << v1[i] << " ";
		}
		cout << endl;

		Vektor<double>v2(v1);

		cout << "v2: ";
		for (int i = 0; i < 5; ++i)
		{
			cout << v2[i] << " ";
		}
		cout << endl;

		Vektor<double>v3(v1);

		cout << "v3: ";
		for (int i = 0; i < 5; ++i)
		{
			cout << v3[i] << " ";
		}
		cout << endl;

		v3 = v2 + v1;

		cout << "v2 + v1: ";
		for (int i = 0; i < 5; ++i)
		{
			cout << v3[i] << " ";
		}
		cout << endl;

		v3 = v2 - v1;

		cout << "v2 - v1: ";
		for (int i = 0; i < 5; ++i)
		{
			cout << v3[i] << " ";
		}
		cout << endl;

		cout << "v1 normaja: " << ~v1 << endl;
		cout << "v2 * v3 = " << v1 * v2 << endl;
		cout << "Euklideszi tav v1 <-> v3 = " << v1 % v3 << endl;

		// Vektor rendben

		cout << endl << endl;

		//Ritka tomb teszt

		double s[5] = { -12.34, 34.56, 0, 11.23, 0 };

		cout << "Ritka tomb muveletek:\n";

		RitkaVektor<double>rv1(s, 5);

		cout << "rv1: ";
		for (int i = 0; i < 5; ++i)
		{
			cout << rv1[i] << " ";
		}
		cout << endl;

		RitkaVektor<double>rv2(rv1);

		cout << "rv2: ";
		for (int i = 0; i < 5; ++i)
		{
			cout << rv2[i] << " ";
		}
		cout << endl;

		RitkaVektor<double>rv3(rv1);

		cout << "rv3: ";
		for (int i = 0; i < 5; ++i)
		{
			cout << rv3[i] << " ";
		}
		cout << endl;

		rv3 = rv2 + rv1;

		cout << "rv2 + rv1: ";
		for (int i = 0; i < 5; ++i)
		{
			cout << rv3[i] << " ";
		}
		cout << endl;

		rv3 = rv2 - rv1;

		cout << "rv2 - rv1: ";
		for (int i = 0; i < 5; ++i)
		{
			cout << rv3[i] << " ";
		}
		cout << endl;

		cout << "rv1 normaja: " << ~rv1 << endl;
		cout << "rv2 * rv3 = " << rv1 * rv2 << endl;
		cout << "Euklideszi tav rv1 <-> rv3 = " << rv1 % rv3 << endl;

		//Ritka tomb rendben

		cout << endl << endl;

		//Konverzio

		cout << "Konverzio:\n";

		RitkaVektor<double> r = v1;

		cout << "Vektor -> RitkaVektor (v1): ";
		for (int i = 0; i < 5; ++i)
		{
			cout << r[i] << " ";
		}
		cout << endl;

		Vektor<double> p = rv1;

		cout << "RitkaVektor -> Vektor (rv1): ";
		for (int i = 0; i < 5; ++i)
		{
			cout << p[i] << " ";
		}
		cout << endl;

	}
	catch (const char* u)
	{
		cout << u << endl;
	}

	return 0;
}