#include <iostream>

using namespace std;
// ez a T az LSP-ben
class tank {
public:
     virtual void javit() {
	cout<<"Tudok Lánctalpat Javítani!"<<endl;
     };
};


class Program {
public:
     void fgv ( tank &tank ) {
          tank.javit();
     }
};



class Pingvin : public tank //  Az ebr tud lánctalpat javítani
{};

int main ( int argc, char **argv )
{
     Program program;
     tank tank;
     program.fgv ( tank );

     Pingvin pingvin;
     program.fgv ( pingvin ); // sérül az LSP, mert a P::fgv megjavítaná az ebr lánctalpát, ami lehetetlen mert az ebr kerekes

}

