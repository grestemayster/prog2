#ifndef TANAR_H
#define TANAR_H

class Tanar {

private:
	string nev;
	Targy tartottTargy;

public:
	string getNev();

	Targy getTartottTargy();

	void hozzaad(int Targy);

	void eltavolit(int Targy);
};

#endif
