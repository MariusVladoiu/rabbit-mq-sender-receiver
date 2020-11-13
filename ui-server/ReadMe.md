server.port=8093

View-ul care creeaza interfata web: railSwitch.html
Se incarca pe Rest Api: http://localhost:8093/railSwitch/all

Controller RailSwitchController - expune cele 2 Rest Api folosite in app.
- /railSwitch/all - GET incarca lista curenta de macazuri ce trebuiesc verificate de acar. Sunt 6 liste; lista curenta e indicata de catre indicator din app verifserver ce poate fi schimbat (prin Rest Api)
- /railSwitch/checkmark/{id}/{checkCommment} - POST salveaza un macaz verificat de acar; parametrii:
    - id - ID-ul macazului
    - checkCommment - comentariul adaugat de acar 
    
In interfata web - railSwitch.html - se foloseste un AJAX pt a face salvarea macazului verificat     

# Descriere aplicatii
In primul rand, pe statia unde se testeaza trebuie sa exista RabbitMQ care sa foloseasca portul 5672 (spring.rabbitmq.port=5672).
Altfel, va trebui modificat in aplicatii, in fisierul application.properties linia spring.rabbitmq.port=5672 cu noul port.

Aplicatiile sunt scrise in Java 8.

Startarea aplicatiilor: 
- app verifserver trebuie startata dupa startarea app work-manager; pt ca work-manager va trebui sa creeze coada queue "railswitch.queue" in cazul cand nu exista (iar la inceput nu va exista).
  Alfel, app verifserver se va opri cu eroare pt ca nu gaseste aceasta coada (queue "railswitch.queue")
- app ui-server poate fi startata oricand
- porturi folosite: ui-server - 8093, verifserver - 8092, work-manager - 8091

Se incarca intr-un browser link-ul http://localhost:8093/railSwitch/all

Tabelul de macazuri o sa fie gol; in schimb, in el vom gasi mesajul: 
    Nu a fost trimisa lista cu macazuri de verificat.
    Ruleaza din browser: http://localhost:8091/rabbitmq/partitionate        
Se incarca in browser si acest link: http://localhost:8091/rabbitmq/partitionate (acest link ne va da mesajul, in noul tab de browser: "Listele de macazuri ce trebuiesc verificate au fost trimise cu succes. Veti vedea in tabel lista de macazuri re-initializata")
Prin rulare acest link 2, se va creea listele de macazuri de verificat (in work-manager) si vor fi trimise catre app verifserver (prin mesaj rabbitMQ)

Se revine la primul link din browser ( http://localhost:8093/railSwitch/all ) si se da refresh.
Va aparea lista de macazuri ce trebuiesc verificate.

Se scrie un mesaj in oricare linie de sub campul <Verificare> si se apasa buton <Trimite> din dreapta lui (aceeiasi linie).

OBS: default, se incarca lista nr. 1 de macazuri. Sunt 6 liste. Se poate schimba lista de macazuri prin Rest Api: 
    http://localhost:8092/to_be_check_switch/set_CurrentGroupId?groupId=2 - groupId este de la 1 la 6
    
    
