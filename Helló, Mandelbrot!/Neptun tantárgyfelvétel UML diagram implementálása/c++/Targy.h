#ifndef TARGY_H
#define TARGY_H

class Targy {

private:
	Tanar targyKod;
	Tanar targyNev;
	Tanar tanar;
	Hallgato hallgato;

public:
	string getTargyKod();

	string getTargyNev();

	Tanar getTanar();

	Hallgato getHallgato();

	void eltavolit(int Hallgato);

	void hozzaad(int Hallgato);
};

#endif
