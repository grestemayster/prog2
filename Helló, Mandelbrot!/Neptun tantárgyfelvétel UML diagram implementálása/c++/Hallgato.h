#ifndef HALLGATO_H
#define HALLGATO_H

class Hallgato {

private:
	int neptunkod;
	string nev;
	Targy felvett;

public:
	int getNeptunkod();

	string getNev();

	Targy getFelvett();

	void felvesz();

	void lead();
};

#endif
