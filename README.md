# Rentific project

“Rentific” je aplikacija za kupovinu nekretnina. Aplikacija će biti korištena od strane uposlenika agencije za nekretnine (staff user) i samog kupca (customer). Neke od mogućnosti staff user-a su login, dodavanje, uređivanje i brisanje nekretnina, slanje računa. Kupac, odnosno customer, se može registrovati, logovati u aplikaciju te onda može pretraživati širok spektar nekretnina, može ih sortirati ili filtrirati na osnovu željenog kriterija. Također, customer ima mogućnost da označi omiljene nekretnine i na taj način ih sačuva za kasnije. Naravno, osnovna funkcionalnost za customer-a jeste rezervisanje ili iznajmljivanje nekretnina. Nakon rezervisanja ili iznajmljivanja nekretnine, customer će dobiti potvrdu o uspješnoj rezervaciji na svoju email adresu zajedno sa računom u pdf formatu koji će potom platiti u banci. Potražnja idealnog stana/kuće/vikendice nije nikad bila lakša!

### Servisi

* user-service
* admin-service
* rent-service
* invoice-service

### Preduslovi

* Java verzija 8+
* Samo jedan korisnik može biti povezan na bazu
* Potrebno je pokrenuti sve servise prije pokretanja aplikacije
* Potrebno je podesiti lokalnu bazu kornika koji pokrece aplikaciju i prilagoditi application.properties konfiguraciju baze

### Pokretanje aplikacije

Izvršiti komandu: ```ng serve``` u folderu servisa i aplikacija ce biti podignuta na portu 4200

Prvo pokrenuti eureka-server

### Pokretanje testova

Izvršiti komandu: ```ng test``` u komandnoj liniji foldera koji sadrzi packet.json fajl

### Pokretanje aplikacije
* Pokrenut RabbitMQ
* Prvo se pokreće eureka-server, pa system-events pa tek onda preostali servisi
* Za admin privilegije u aplikaciji, prvo je neophodno kroz postman dodati rolu admin, kao i usera koji ce imati tu rolu
* Za user privilegije je dovoljna registracija na aplikaciji

### Docker
* Na branchu docker se nalaze kreirani Docker fajlovi  i docker-compose.yaml fajl u kojem su svi podaci za pokretanje aplikacije
* Za pokretanje aplikacije kroz docker potrebno je pokrenuti u cmd-u docker-compose up i pokrenuti frontend na localhost:80

### Ideje za unapredjenje aplikacije
* Slike su cuvane lokalno u bazi, tako da bi sljedeci korak bio njihovo spasavanje i dobavljanje sa externog storage-a 

### Posao uradjen tokom vikenda nakon zadnjih laboratorijskih vjezbi
* Refactoring koda
* UI promjene
* Frontend testovi
* Dodati svi ostali kontejneri u docker fajl
* Doradjena dokumentacija

### Demo snimci



