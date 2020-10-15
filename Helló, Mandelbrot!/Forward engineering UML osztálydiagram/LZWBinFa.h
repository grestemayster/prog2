#ifndef LZWBINFA_H
#define LZWBINFA_H

class LZWBinFa {

protected:
	Csomopont _gyoker;
private:
	int _melyseg;
	int _atlagosszeg;
	int _atlagdb;
	double _szorasosszeg;
protected:
	int _maxMelyseg;
	double _atlag;
	double _szoras;

public:
	void _LZWBinFa();

	void getMelyseg();

	void getAtlag();

	void getSzoras();

	LZWBinFa();

	void kiir();

private:
	void _();

	void szabadit();

protected:
	void rmelyseg();

	void ratlag();

	void rszoras();
};

#endif
