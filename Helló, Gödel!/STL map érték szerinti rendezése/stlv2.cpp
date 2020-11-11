#include <map>
#include <iostream>
#include <algorithm>
#include <vector>

std::vector<std::pair<std::string, int>> sort_map ( std::map <std::string, int> map)
{
        std::vector<std::pair<std::string, int>> ordered;

        for ( auto & i : map ) {
                if ( i.second ) {
                        std::pair<std::string, int> p {i.first, i.second};
                        ordered.push_back ( p );
                }
        }

        std::sort (
                std::begin ( ordered ), std::end ( ordered ),
        [ = ] ( auto && p1, auto && p2 ) {
                return p1.second > p2.second;
        }
        );

        return ordered;
}

int main()
{
	std::map<std::string, int> map;
	map["a"]=33;
	map["b"]=45;
	map["c"]=7;

	std::vector<std::pair<std::string, int>> sorted = sort_map(map);

	for(auto & i : sorted)
	{
		std::cout <<i.first << " " <<i.second << std::endl;
	}
}
