#include "matrix.h"
#include<iostream>

#define MAX_SIZE 10000

// Létrehoz egy négyzetes mátrixot
matrix* create(int new_x)
{
	if (new_x < 1 || new_x > MAX_SIZE)
		return 0;
	int i;
	matrix* A = (matrix*)calloc(1, sizeof(matrix));

	if (!A)
		return 0;

	A->m = (int**)calloc(new_x, sizeof(int*));

	if (!A->m)
		return 0;

	bool allocation_complete = true;
	for (i = 0; i < new_x; ++i)
	{
		A->m[i] = (int*)calloc(new_x, sizeof(int));
		if (!A->m[i])
			allocation_complete = false;
	}

	if (!allocation_complete)
		return 0;

	A->x = new_x;
	A->y = new_x;
	return A;
}

// Létrehoz egy mátrixot
matrix* create(int new_x, int new_y)
{
	if (new_x < 1 || new_x > MAX_SIZE || new_y < 1 || new_y > MAX_SIZE)
		return 0;
	int i;
	matrix* A = (matrix*)calloc(1, sizeof(matrix));

	if (!A)
		return 0;

	A->m = (int**)calloc(new_x, sizeof(int*));

	if (!A->m)
		return 0;

	bool allocation_complete = true;
	for (i = 0; i < new_x; ++i)
	{
		A->m[i] = (int*)calloc(new_y, sizeof(int));
		if (!A->m[i])
			allocation_complete = false;
	}

	if (!allocation_complete)
		return 0;

	A->x = new_x;
	A->y = new_y;
	return A;
}

// Felszabadítja a lefoglalt mátrixot
void destroy(matrix*A)
{
	int i;
	for (i = 0; i < A->x; ++i)
		free(A->m[i]);
	free(A->m);
	free(A);
}

//Inicializálja a mátrixot a megadott értékkel
bool init(matrix* A, int new_element)
{
	if (!A || new_element < 0 || new_element > 255)
		return false;
	int i, j;
	for (i = 0; i < A->x; ++i)
	{
		for (j = 0; j < A->y; ++j)
			A->m[i][j] = new_element;
	}
	return true;
}

//Inicializálja a mátrixot 0-val
bool init(matrix* A)
{
	if (!A)
		return false;
	int i, j;
	for (i = 0; i < A->x; ++i)
	{
		for (j = 0; j < A->y; ++j)
			A->m[i][j] = 0;
	}
	return true;
}

// Visszatéríti a mátrix sorainak számát
int get_size_x(matrix*A)
{
	return A->x;
}

// Visszatéríti a mátrix oszlopainak számát
int get_size_y(matrix*A)
{
	return A->y;
}

// Visszatéríti a megadott koordinátán lévő elem értékét
int get_value(matrix*A, int i, int j)
{
	if (i >= 0 && i < A->x && j >= 0 && j < A->y)
		return A->m[i][j];
	else
		return 0;
}

// Beállítja a megadott koordinátán lévő elem értékét
int set_value(matrix*A, int i, int j, int new_value)
{
	if (i >= 0 && i < A->x && j >= 0 && j < A->y && new_value >= 0 && new_value < 256)
	{
		A->m[i][j] = new_value;
		return A->m[i][j];
	}
	else
		return 0;
}

// Újraméretezi a mátrixot négyzetessé a megadott méretek szerint
bool resize(matrix*A, int new_x)
{
	if (new_x < 1 || new_x > 1920)
		return false;
	int i;
	if (new_x > A->x)
	{
		int** tmp1 = (int**)realloc(A->m, new_x * sizeof(int*));
		if (tmp1)
			A->m = tmp1;
		else
			return false;
		int* tmp2;
		for (i = 0; i < new_x; ++i)
		{
			if (i < A->x)
			{
				tmp2 = (int*)realloc(A->m[i], new_x * sizeof(int));
				if (tmp2)
					A->m[i] = tmp2;
				else
					return false;
			}
			else
			{
				tmp2 = (int*)calloc(new_x, sizeof(int));
				if (tmp2)
					A->m[i] = tmp2;
				else
					return false;
			}
		}
	}
	else
	{
		for (i = new_x; i < A->x; ++i)
			free(A->m[i]);

		int** tmp1 = (int**)realloc(A->m, new_x * sizeof(int*));
		if (tmp1)
			A->m = tmp1;
		else
			return false;
		int* tmp2;
		for (i = 0; i < new_x; ++i)
		{
			tmp2 = (int*)realloc(A->m[i], new_x * sizeof(int));
			if (tmp2)
				A->m[i] = tmp2;
			else
				return false;
		}
	}
	A->x = new_x;
	A->y = new_x;
	return true;
}

// Újraméretezi a mátrixot a megadott méretek szerint
bool resize(matrix*A, int new_x, int new_y)
{
	if (new_x < 1 || new_x > 1920 || new_y < 1 || new_y > 1920)
		return false;
	int i;
	if (new_x > A->x)
	{
		int** tmp1 = (int**)realloc(A->m, new_x * sizeof(int*));
		if (tmp1)
			A->m = tmp1;
		else
			return false;
		int* tmp2;
		for (i = 0; i < new_x; ++i)
		{
			if (i < A->x)
			{
				tmp2 = (int*)realloc(A->m[i], new_y * sizeof(int));
				if (tmp2)
					A->m[i] = tmp2;
				else
					return false;
			}
			else
			{
				tmp2 = (int*)calloc(new_y, sizeof(int));
				if (tmp2)
					A->m[i] = tmp2;
				else
					return false;
			}
		}
	}
	else
	{
		for (i = new_x; i < A->x; ++i)
			free(A->m[i]);

		int** tmp1 = (int**)realloc(A->m, new_x * sizeof(int*));
		if (tmp1)
			A->m = tmp1;
		else
			return false;
		int* tmp2;
		for (i = 0; i < new_x; ++i)
		{
			tmp2 = (int*)realloc(A->m[i], new_y * sizeof(int));
			if (tmp2)
				A->m[i] = tmp2;
			else
				return false;
		}
	}
	A->x = new_x;
	A->y = new_y;
	return true;
}

// Az első mátrixot átmásolja a második mátrixba
bool copy(matrix*A, matrix*&B)
{
	if (!A)
		return false;
	bool resize_complete = true;
	if (!B)
		B = create(A->x, A->y);
	else
		resize_complete = resize(B, A->x, A->y);

	if (!B || !resize_complete)
		return false;
	int i, j;
	for (i = 0; i < A->x; ++i)
		for (j = 0; j < A->y; ++j)
			B->m[i][j] = A->m[i][j];
	return true;
}

// Elfordítja a mátrixot balra
void rotate_left(matrix*A)
{
	matrix* B = create(A->y, A->x);
	int i, j;
	for (i = 0; i < A->x; ++i)
		for (j = 0; j < A->y; ++j)
			B->m[(A->y) - j - 1][i] = A->m[i][j];
	copy(B, A);
	destroy(B);
}

// Elfordítja a mátrixot jobbra
void rotate_right(matrix*A)
{
	matrix* B = create(A->y, A->x);
	int i, j;
	for (i = 0; i < A->x; ++i)
		for (j = 0; j < A->y; ++j)
			B->m[j][(A->x) - i - 1] = A->m[i][j];
	copy(B, A);
	destroy(B);
}