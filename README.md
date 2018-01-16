# MIVS
Mokymosi įstaigos valdymo sistema

**PIRMAS ETAPAS**

PROJEKTO REIKALAVIMAI:

 - Projekto kodas saugomas github'e  Projektui valdyti naudojamas maven
 -    Visa programos veikimo metu sukurta informacija saugoma failuose
 -    Programos logika padengta unit testais Vartotojo sąsajai naudojama
   konsolė

FUNKCIONALUMO REIKALAVIMAI:

 - Prie sistemos galima prisijungti suvedus username ir password.
 - Pirmą kartą paleidus programą turi egzistuoti vartotojas admin su
   slaptažodžiu admin, kuris turėtų admin rolę ir galėtų atlikti visas
   operacijas programoje.
 - Sistemoje naujus vartotojus gali užsiregistruoti tik vartotojai su
   admin role
 

>  įvedę:
>  
> >  firstName, secondName, password, userName
>   
>   pasirinkę:
>  >   rolę iš admin, lecturer, student

 - Vartotojai turintys rolę lecturer arba student gali turėti ir keisti,
   pridėti informaciją apie save
>  userName
>  firstName
>     lastName
>     personalNumber
>     dateOfBirth
>     email
>     mobileNumber
>     gender
>     address
>     runningCourses (kursai, kuriuos veda)

    Papildyta
    studentCode - negali būti keičiamasturi, turi būti unikalus ir sukuriamas naujo vartotojo kūrimo metu.
    lecturerCode - negali būti keičiamasturi, turi būti unikalus ir sukuriamas naujo vartotojo kūrimo metu.

 - Vartotojai turintys rolę admin gali:

 

>  - pridėti  `  course    code   tittle   desciption   startDate   credit   lecturerCode`
>  - pamatyti visų studentų sąrašą
>  - pamatyti visų kursų sąrašą

 - Vartotojai su student role gali:


>- matyti visų kursų sąrašą matyti užsiregistruotų kursų sąrašą
>- užsiregistruoti į kursą kai:
>>- kurso startDate yra ankstenė už dabatinę 
>>-  bendra užseregistuotų kursų kreditų suma mažesnė us 12

 - Vartotojai su lecturer role gali:

>- matyti jam priskirtus kursus 
>- matyti į kursą užsiregistravusius studentus


**Antras etapas**

PAPILDOMI PROJEKTO REIKALAVIMAI:

Visos su vartotoju nesusijusios žinutės turi būti loginamos į log.out failą.