#ifndef matrix_declaration
#define matrix_declaration
typedef struct matrix
{
	// A mátrixra mutató pointer
	int** m;
	// A mátrix méretei: x - sorok száma, y - oszlopok száma
	int x, y;
} matrix;

// Létrehoz egy négyzetes mátrixot
matrix* create(int);

// Létrehoz egy mátrixot
matrix* create(int, int);

// Felszabadítja a lefoglalt mátrixot
void destroy(matrix*);

//Inicializálja a mátrixot a megadott értékkel
bool init(matrix*, int);

//Inicializálja a mátrixot 0-val
bool init(matrix*);

// Visszatéríti a mátrix sorainak számát
int get_size_x(matrix*);

// Visszatéríti a mátrix oszlopainak számát
int get_size_y(matrix*);

// Visszatéríti a megadott koordinátán lévő elem értékét
// A két int paramnéter a pozíció koordinátái
int get_value(matrix*, int, int);

// Beállítja a megadott koordinátán lévő elem értékét
// Az első két int paraméter az elem koordinátái, a harmadik az érték
int set_value(matrix*, int, int, int);

// Újraméretezi a mátrixot a megadott méretek szerint
bool resize(matrix*, int, int);

// Újraméretezi a mátrixot n;gyzetessé a megadott méretek szerint
bool resize(matrix*, int);

// Az első mátrixot átmásolja a második mátrixba
// Ha nincs létrehozva a második mátrix, létrehozza
bool copy(matrix*, matrix*&);

// Elfordítja a mátrixot balra
void rotate_left(matrix*);

// Elfordítja a mátrixot jobbra
void rotate_right(matrix*);

#endif
