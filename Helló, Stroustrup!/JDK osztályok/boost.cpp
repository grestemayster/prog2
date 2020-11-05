#include <iostream>
#include <string>
#include <fstream>
#include <iomanip>
#include <vector>

#include <boost/filesystem.hpp>

using namespace std;

int counter=0;

void read_classes (boost::filesystem::path path, vector<string> acts)
{
    if(is_regular_file(path))
    {
    string ext(".java");
    if(!ext.compare(boost::filesystem::extension (path))) 
    {
        cout<<path.string()<<'\n';
        string actjavaspath=path.string();
        size_t end = actjavaspath.find_last_of("/");
        string act = actjavaspath.substr(0,end);
        acts.push_back(act);
        counter++;
    }
}
else if(is_directory(path))
    for(boost::filesystem::directory_entry & entry :
        boost::filesystem::directory_iterator (path))
    read_classes(entry.path(),acts);
 }

int main( int argc, char *argv[])
{
    
    vector<string> acts;
    read_classes("src", acts);
    cout << "Ennyi Java osztalyt talaltunk: "<< counter << "\n";

}
