# ifndef ROBOCAR_MYSHMCLIENT_HPP
# define  ROBOCAR_MYSHMCLIENT_HPP

/ * *
 * @rövid Justine - ez egy gyors prototípus a Robocar City Emulator fejlesztéséhez
 *
 * @file myshmclient.hpp
 * @author Bátfai Norbert <nbatfai@gmail.com>
 * @változat 0.0.10
 *
 * @ szakasz ENGEDÉLY
 *
 * Szerzői jog (C) 2014 Bátfai Norbert, batfai.norbert@inf.unideb.hu
 *
 * Ez a program ingyenes szoftver: terjesztheti és / vagy módosíthatja
 * a GNU által kiadott Általános Nyilvános Licenc feltételei szerint
 * a Free Software Foundation, a licenc 3. verziója, vagy
 * (tetszés szerint) bármely újabb verzió.
 *
 * Ezt a programot abban a reményben terjesztjük, hogy hasznos lesz,
* de GARANCIA NÉLKÜL; még a hallgatólagos garancia nélkül is
* ELADHATÓSÁG vagy FELHATÓSÁG KÜLÖNLEGES CÉLRA. Lásd a
 * További részletekért a GNU General Public License.
 *
 * Meg kellett volna kapnia a GNU General Public License másolatát
* ezzel a programmal együtt. Ha nem, akkor lásd: <http://www.gnu.org/licenses/>.
 *
 * @section LEÍRÁS
 * Robocar City Emulator és Robocar World Championship
 *
 * desc
 *
 * /

# include  < boost / interprocess / managed_shared_memory.hpp >
# include  < boost / interprocess / allokátorok / allokátor.hpp >
# include  < boost / interprocess / container / map.hpp >
# include  < boost / interprocess / container / vector.hpp >
# include  < boost / interprocess / container / string.hpp >

# include  < smartcity.hpp >
# include  < car.hpp >

# include  < cstdlib >
# include  < iterátor >

# include  < boost / asio.hpp >

# include  < korlátok >

# include  < memória >

# include  < boost / graph / adjacency_list.hpp >
// #include <boost / graph / graph_utility.hpp>
# include  < boost / graph / graph_traits.hpp >
# include  < boost / graph / dijkstra_shortest_paths.hpp >

# include  < boost / graph / properties.hpp >
# include  < boost / tulajdonság_térkép / tulajdonság_térkép.hpp >

# include  < shmclient.hpp >

# include  < algoritmus >

# ifdef DEBUG
# include  < iostream >
# include  < chrono >
# endif

# include  < boost / graph / bellman_ford_shortest_paths.hpp >

# include  < boost / graph / graphviz.hpp >
# include  < fstream >

névtér  Justine
{
névtér  mintavevő
{

typedef boost :: adjacency_list <boost :: listS, boost :: vecS, boost :: directionS,
        boost :: tulajdonság <boost :: vertex_name_t , osmium :: unsigned_object_id_type>,
        boost :: property <boost :: edge_weight_t , int >> NodeRefGraph;

typedef boost :: graph_traits <NodeRefGraph> :: vertex_descriptor NRGVertex;
typedef boost :: graph_traits <NodeRefGraph> :: vertex_iterator NRGVertexIter;
typedef boost :: graph_traits <NodeRefGraph> :: edge_descriptor NRGEdge;
typedef boost :: graph_traits <NodeRefGraph> :: edge_iterator NRGEdgeIter;

typedef boost :: property_map <NodeRefGraph, boost :: vertex_name_t > :: type VertexNameMap;
typedef boost :: property_map <NodeRefGraph, boost :: vertex_index_t > :: type VertexIndexMap;

typedef boost :: iterator_property_map <NRGVertex *, VertexIndexMap, NRGVertex, NRGVertex &> ElődMap;
typedef boost :: iterator_property_map < int *, VertexIndexMap, int , int &> DistanceMap;

typedef boost :: property_map <NodeRefGraph, boost :: edge_weight_t > :: type EdgeWeightMap;

/ * *
 * @brief Az útválasztási algoritmusok tesztelésére használt mintaosztály.
 *
 * Ez a minta osztály bemutatja, hogy az ügyfélügynökök hogyan hozhatnak létre BGL grafikont az adatokból, amelyek megtalálhatók a megosztott memóriában.
 *
 * @author Bátfai Norbert
 * @dátum, 2014. december 7
 * /
osztály  MyShmClient : nyilvános  ShmClient
{
nyilvános:

  / * *
   * @brief Ez a konstruktor létrehozza a BGL gráfot a térképgráfból.
   * @param shm_segment a megosztott memória objektum neve
   *
   * Ez a konstruktor létrehozza a BGL gráfot abból a térképgráfból
   * a megosztott memória szegmensbe kerül.
   * /
  MyShmClient ( const  char * shm_segment, std :: string teamname): ShmClient (shm_segment), m_teamname (csapatnév)
  {

    nr_graph = bgl_graph ();

# ifdef DEBUG
    print_vertices ( 10 );
    print_edges ( 10 );
    std :: fstream graph_log (csapatnév + " .dot " , std :: ios_base :: out);
    boost: write_graphviz (graph_log, * nr_graph);		   
# endif

  }

  / * *
   * @ röviden Dtor
   *
   * /
  ~ MyShmClient ()
  {

    törli az nr_graph;

  }

  / * *
   * @brief Ez a funkció elindítja az ügyfelet.
   * @param io_service
   * @param port a forgalmi kiszolgáló TCP portja
   *
   * Ez a módszer a következőket teszi: lekér egy értéket a megosztott memóriából,
   * majd végül kapcsolatot létesít a forgalmi kiszolgálóval
   * küld néhány kliens parancsot.
   * /
  void  start (boost :: asio :: io_service & io_service, const  char * port);

  void  start10 (boost :: asio :: io_service & io_service, const  char * port);

  / * *
   * @brief Ez a függvény megszámolja a térképgráf csúcsainak és éleinek számát.
   * @param [out] összegezi az élek számát
   * @ visszaállítja a csúcsok számát
   *
   * Ez a függvény megszámolja a térképgráf csúcsainak és éleinek számát
   * a megosztott memória szegmensbe kerül.
   * /
  int  szám_felületek ( int & sum_edges)
  {

    std :: set <osmium :: unsigned_object_id_type> sum_vertices;

    mert (justine :: robocar :: shm_map_Type :: iterator iter = shm_map-> begin ();
          iter! = shm_map-> end (); ++ iter)
      {

        sum_vertices. beillesztés (iter-> first );
        sum_edges + = iter-> másodperc . m_alist . méret ();

        mert ( auto noderef: iter-> second . m_alist )
          {
            sum_vertices. betét (noderef);
          }

      }

    return sum_vertices. méret ();
  }

  / * *
   * @brief Ez a funkció kinyomtatja a térképdiagram széleit.
   * @param több a nyomtatott tételek maximális száma
   *
   * /
  void  print_edges ( további aláíratlan )
  {

    VertexNameMap vertexNameMap = boost :: get (boost :: vertex_name, * nr_graph);

    std :: pár <NRGVertexIter, NRGVertexIter> vi;

    aláíratlan számlálás { 0 };

    for (vi = boost :: csúcsok (* nr_graph); vi. első ! = vi. második ; ++ vi. első )
      {
        ha (több)
          ha (++ szám> több) megszakad ;

        std :: cout << vertexNameMap [* vi. első ] <<   "  " ;
      }
    std :: cout << std :: endl;

  }

  / * *
   * @brief Ez a funkció kinyomtatja a térképgráf csúcsait.
   * @param több a nyomtatott tételek maximális száma
   *
   * /
  void  print_vertices ( további aláíratlan )
  {
    VertexNameMap vertexNameMap = boost :: get (boost :: vertex_name, * nr_graph);

    aláíratlan számlálás { 0 };

    osmium :: unsigned_object_id_type prev = 0 ;
    NRGEdgeIter ei, ei_end;

    for ( boost :: tie (ei, ei_end) = boost :: élek (* nr_graph); ei! = ei_end; ++ ei)
      {
        auto ii = vertexNameMap [ boost :: forrás (* ei, * nr_graph)];

        ha (ii! = előző)
          std :: cout << std :: endl;

        std :: cout << " ( " << ii
                  << " -> " << vertexNameMap [ boost :: target (* ei, * nr_graph)] << " ) " ;

        prev = ii;

        ha (több)
          ha (++ szám> több) megszakad ;

      }

    std :: cout << std :: endl;

  }

  / * *
   * @brief Ez a funkció létrehozza a BGL grafikont.
   * @return a létrehozott BGL gráf mutatója.
   *
   * /
  NodeRefGraph * bgl_graph ( érvénytelen )
  {

    NodeRefGraph * nr_graph = new  NodeRefGraph ();

    int szám { 0 };

    mert (justine :: robocar :: shm_map_Type :: iterator iter = shm_map-> begin ();
          iter! = shm_map-> end (); ++ iter)
      {

        osmium :: unsigned_object_id_type u = iter-> first ;

        mert (justine :: robocar :: uint_vector :: iterator noderefi = iter-> second . m_alist . begin ();
              noderefi! = iter-> másodperc . m_alist . vége ();
              ++ noderefi)
          {

            NodeRefGraph :: vertex_descriptor f;
            std :: map <osmium :: unsigned_object_id_type, NRGVertex> :: iterátor it = nr2v. megtalálja (u);

            if (it == nr2v. vég ())
              {

                f = boost :: add_vertex (u, * nr_graph);
                nr2v [u] = f;

                ++ számít;

              }
            más
              {

                f = it-> másodperc ;

              }

            NodeRefGraph :: vertex_descriptor t;
            it = nr2v. lelet (* noderefi);
            if (it == nr2v. vég ())
              {

                t = boost :: add_vertex (* noderefi, * nr_graph);
                nr2v [* noderefi] = t;

                ++ számít;

              }
            más
              {

                t = it-> másodperc ;

              }

            int to = std :: távolság ( iter- > second . m_alist . begin (), noderefi);

            boost :: add_edge (f, t, palist ( iter- > first , to), * nr_graph);

          }

      }

# ifdef DEBUG
    std :: cout << " # csúcsok száma: " << count << std :: endl ;;
    std :: cout << " # BGF élek: " << boost :: num_edges (* nr_graph) << std :: endl ;;
    std :: cout << " # BGF csúcsok: " << boost :: num_vertices (* nr_graph) << std :: endl ;;
# endif

    return nr_graph;
  }

  / * *
   * @brief Ez a függvény megoldja a legrövidebb út problémáját a Dijkstra algoritmus segítségével.
   * @param forrás a forrás csomópont
   * @param célozza meg a cél csomópontot
   * @vissza a legrövidebb utat a csomópontok forrás és cél között
   *
   * Ez a függvény meghatározza a legrövidebb utat a forrás csomóponttól a cél csomópontig.
   * /
  std :: vector < osmium :: unsigned_object_id_type> hasDijkstraPath ( osmium :: unsigned_object_id_type from, osmium :: unsigned_object_id_type to)
  {

# ifdef DEBUG
    automatikus indítás = std :: chrono :: high_resolution_clock :: now ();
# endif

    std :: vector <NRGVertex> szülők ( boost :: num_vertices (* nr_graph));
    std :: vector < int > távolságok ( boost :: num_vertices (* nr_graph));

    VertexIndexMap vertexIndexMap = boost :: get (boost :: vertex_index, * nr_graph);

    PredecessorMap predecessorMap (& szülők [ 0 ], vertexIndexMap);
    DistanceMap distanceMap (& távolságok [ 0 ], vertexIndexMap);

    boost :: dijkstra_shortest_paths (* nr_graph, nr2v [from],
                                     boost: távolság_térkép (distanceMap). előd_térkép (elődMAP));

    VertexNameMap vertexNameMap = boost :: get (boost :: vertex_name, * nr_graph);

    std :: vector <osmium :: unsigned_object_id_type> elérési út;

    NRGVertex tov = nr2v [ig];
    NRGVertex fromv = elődMap [tov];

# ifdef DEBUG
    int dist { 0 };
# endif

    míg (fromv! = tov)
      {

        NRGEdge edge = boost :: edge (fromv, tov, * nr_graph). először ;

# ifdef DEBUG
        std :: cout << vertexNameMap [ boost :: forrás (edge, * nr_graph)]
                  << " -> "
                  << vertexNameMap [ boost :: target (edge, * nr_graph)] << std :: endl;
        dist + = distanceMap [fromv];
# endif

        pálya. push_back (vertexNameMap [ boost :: target (edge, * nr_graph)]);

        tov = fromv;
        fromv = elődMap [tov];
      }
    pálya. push_back (from);

    std :: fordított (elérési út kezdete (), útvonal vége ());

# ifdef DEBUG
    std :: cout << std :: chrono :: duration_cast <std :: chrono :: milliszekundum> (
                std :: chrono :: high_resolution_clock :: now () - start). gróf ()
              << " ms " << dist << " méter " << std :: endl;

    std :: copy (elérési út kezdete (), elérési útja vége (),
                std :: ostream_iterator <osmium :: unsigned_object_id_type> (std :: cout, "  " ));
# endif

    visszatérési út;
  }


  / * *
   * @brief Ez a funkció megoldja a legrövidebb út problémáját a Bellman-Ford algoritmus segítségével.
   * @param forrás a forrás csomópont
   * @param célozza meg a cél csomópontot
   * @vissza a legrövidebb utat a csomópontok forrás és cél között
   *
   * Ez a függvény meghatározza a legrövidebb utat a forrás csomóponttól a cél csomópontig.
   * /
  std :: vector < osmium :: unsigned_object_id_type> hasBellmanFordPath ( osmium :: unsigned_object_id_type from, osmium :: unsigned_object_id_type to)
  {

# ifdef DEBUG
    automatikus indítás = std :: chrono :: high_resolution_clock :: now ();
# endif

    std :: vector <NRGVertex> szülők ( boost :: num_vertices (* nr_graph));
    for ( int i = 0 ; i < boost :: num_vertices (* nr_graph); ++ i)
      szülők [i] = i;

    std :: vector < int > távolságok ( boost :: num_vertices (* nr_graph), (std :: numeric_limits < int > :: max) ());
    távolságok [nr2v [tól]] = 0 ;

    VertexIndexMap vertexIndexMap = boost :: get (boost :: vertex_index, * nr_graph);
    EdgeWeightMap weightMap = boost :: get ( boost :: edge_weight_t (), * nr_graph);

    PredecessorMap predecessorMap (& szülők [ 0 ], vertexIndexMap);
    DistanceMap distanceMap (& távolságok [ 0 ], vertexIndexMap);

    boost: bellman_ford_shortest_paths (* nr_graph, boost :: num_vertices (* nr_graph),
                                         boost :: súly_térkép (weightMap).
                                         távolság_térkép (távolságMap). előd_térkép (elődMAP));

    VertexNameMap vertexNameMap = boost :: get (boost :: vertex_name, * nr_graph);

    std :: vector <osmium :: unsigned_object_id_type> elérési út;

    NRGVertex tov = nr2v [ig];
    NRGVertex fromv = elődMap [tov];

# ifdef DEBUG
    int dist { 0 };
# endif

    míg (fromv! = tov)
      {

        NRGEdge edge = boost :: edge (fromv, tov, * nr_graph). először ;

# ifdef DEBUG
        std :: cout << vertexNameMap [ boost :: forrás (edge, * nr_graph)]
                  << " -> "
                  << vertexNameMap [ boost :: target (edge, * nr_graph)] << std :: endl;
        dist + = distanceMap [fromv];
# endif

        pálya. push_back (vertexNameMap [ boost :: target (edge, * nr_graph)]);

        tov = fromv;
        fromv = elődMap [tov];
      }
    pálya. push_back (from);

    std :: fordított (elérési út kezdete (), útvonal vége ());

# ifdef DEBUG
    std :: cout << std :: chrono :: duration_cast <std :: chrono :: milliszekundum> (
                std :: chrono :: high_resolution_clock :: now () - start). gróf ()
              << " ms " << dist << " méter " << std :: endl;

    std :: copy (elérési út kezdete (), elérési útja vége (),
                std :: ostream_iterator <osmium :: unsigned_object_id_type> (std :: cout, "  " ));
# endif

    visszatérési út;
  }


védett:

  NodeRefGraph * nr_graph;
  std :: string m_teamname;

magán:

  / * *
   * Segítő struktúra a BGL grafikon létrehozásához.
   * /
  std :: map <osmium :: unsigned_object_id_type, NRGVertex> nr2v;

  / * *
   * A legrövidebb útkeresés teszteléséhez.
   * /
  void  foo ( érvénytelen )
  {

    std :: cout << std :: endl;
    std :: vector <osmium :: unsigned_object_id_type> pathD = hasDijkstraPath ( 2969934868 , 1348670117 );
    std :: cout << std :: endl;
    std :: copy (elérési út: kezdet (), elérési út: vég (),
                std :: ostream_iterator <osmium :: unsigned_object_id_type> (std :: cout, "   -D-> " ));

    std :: vector <osmium :: unsigned_object_id_type> pathBF = hasBellmanFordPath ( 2969934868 , 1348670117 );
    std :: cout << std :: endl;
    std :: copy (elérési út BF kezdete (), elérési útja vége (),
                std :: ostream_iterator <osmium :: unsigned_object_id_type> (std :: cout, " -BF-> " ));

    std :: cout << std :: endl;
    pathD = hasDijkstraPath ( 2969934868 , 1402222861 );
    std :: copy (elérési út: kezdet (), elérési út: vég (),
                std :: ostream_iterator <osmium :: unsigned_object_id_type> (std :: cout, "   -D-> " ));

    std :: cout << std :: endl;
    pathBF = hasBellmanFordPath ( 2969934868 , 1402222861 );
    std :: cout << std :: endl;
    std :: copy (elérési út BF kezdete (), elérési útja vége (),
                std :: ostream_iterator <osmium :: unsigned_object_id_type> (std :: cout, " -BF-> " ));

  }

  int  init (boost :: asio :: ip :: tcp :: socket & socket);

  strukturálja a  SmartCart
  {
    int id;
    nem aláírt ;
    aláíratlan ;
    int lépés;
  };

  typedef SmartCar Gengszter;
  typedef  int Cop;

  std :: vector <Gengszter> gengszterek (boost: asio :: ip :: tcp :: socket & socket, int id, osmium :: unsigned_object_id_type cop);
  std :: vector <Cop> initcops (boost: asio :: ip :: tcp :: socket & socket);
  void  pos (boost: asio :: ip :: tcp :: socket & socket, int id);
  void  car (boost :: asio :: ip :: tcp :: socket & socket, int id, unsigned * f, unsigned * t, unsigned * s);
  érvénytelen  útvonal (boost :: asio :: ip :: tcp :: socket & socket, int id, std :: vector <osmium :: unsigned_object_id_type> &);
};

}
} // justine :: sampleclient ::

# endif  // ROBOCAR_SHMCLIENT_HPP