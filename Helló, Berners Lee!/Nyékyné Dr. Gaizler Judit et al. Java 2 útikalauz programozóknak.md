# 1.fejezet
-	A Java is egy objektumorientált nyelv a C++ nyelvhez hasonlóan. A Java nyelvben írt program is 
objektumokbó illetve osztályokból áll, az osztályok pedig metódusokból és változókból, ahogyan a C++ ban írt program is.
A könyv egy "Hello,World!"-el mutatja be a Java fordító működését, ahol egy interpretes, azaz egy Java virtuális gép 
értelmezi a fordítóprogram révén létrehozott bájtkódot. Ez eltér a C++ működésétől. 
A Java a web-en szerezte a sikerét lényegében. Ez szintén eltérés a C++ nyelvhez képest.
A könyv szerzői itt egy HTML oldalba beépített programot mutat be példának. Ezeket a programokat appletek-nek nevezünk.
Az applet forráskódjában megtalálható a feladat végrehajásához szükséges könyvtárakat elérhetővé tevő "import" szó.
A következő 3 alfejezet először a változókkal majd a konstansokkal végezetűl pedig a megjegyzésekkel foglalkozik.
A változók típusa lehet:
    * boolean - logikai true vagy false értékkel
    * char - 16 bites Unicode karakter
    * byte - 8 bites előjeles egész szám
    * short - 16 bites előjeles egész szám
    * int - 32 bites előjeles egész szám
    * long - 64 bites előjeles egész szám
    * float - 32 bites lebegőpontos racionális szám
    * double - 64 bites lebegőpontos racionális szám
A konstansok ugyanúgy adhatóak meg, mint C++ esetén, a "final" kulcsszóval.
A megjegyzések Java-ban is ugyanúgy lehetnek 1 vagy több sorosak, akár még dokumentációsak is.
A Java nyelvben az osztályok a "class" kulcsszó használatával hozható létre.Az adattagjai illetve metódusai szabad
sorrendben felsorolhatóak. Ha a "public" kulcsszót használjuk, akkor a külvilág számára láthtató, azonban ha a "protected"
 vagy a "private" kulcsszavakat használjuk, akkor az ekőzőnél csak a leszármazottak, utóbbi esetben viszot senki másnak sem látható.
A könyv a példákban megjelenít egy új tipust, a "String"-et. Ez az osztály a karakterláncok kezelésére lett létrehozva, ami eltér a
C++ hoz képest, ahol ez a típus a karakterek tömbje volt.
Ha az osztályunk megvan, akkor a "new" operátort segítségűl véve objektumot is létre tudunk hozni.
Az operátor az új objektum számára helyet foglal le és erre a területre vonatkozó referenciával tér vissza. 
A könyv említi a "static" kulcsszót is, amellyel ha deklarálunk egy elemet, akkor az elem magához az osztályhoz
fog tartozni. A Java-ban is fontos hogy egy nem elvárt helyzetben a program hogyan is reagál. Ez a kivételkezelés, 
amely C++ ban is így működik.
A könyv kitér a biztonságra is, amit az adott igényhez lehet igazítani.Ennek a segítségével megtudjuk határozni
hogy egy adott kódrészletnek mihez is van hozzáférése.
Az AWT (Abstract Window Toolkit) nagyban segít a program felhasználói grafikai felület 
kivitelezésében.