#include <iostream>

using namespace std;

class Szulo
{

};
    
class Gyerek: public Szulo
{
    void viselkedik()
    {
	   cout<<"Viselkedek!";	
	}
};
    
int main ()
{
    Szulo* sz= new Gyerek;
   
    cout<<sz->viselkedik();      
}

