#ifndef CSOMOPONT_H
#define CSOMOPONT_H

class Csomopont {

private:
	char betu;
	Csomopont jobbEgy;
	Csomopont balNulla;

public:
	void egyesGyermek();

	void nullasGyermek();

	void getBetu();

	Csomopont(char aB = '/');

	void _Csomopont();

	void ujEgyesGyermek(Csomopont* aGy);

	void ujNullasGyermek(Csomopont* aGy);

private:
	Csomopont(Csomopont const& aUnnamed_1);

	void _(Csomopont const& aUnnamed_1);
};

#endif
