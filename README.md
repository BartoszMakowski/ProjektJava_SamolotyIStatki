# ProjektJava_SamolotyIStatki
#####Projekt w Javie realizowany indywidualnie na zaliczenie laboratorium z programowania obiektowego na 3. semestrze studiów inżynierskich dziennych na kierunku informatyka na Politechnice Poznańskiej.

##Krótki opis projektu:
Celem projektu jest zamodelowanie i wizualizacja świata samolotów i statków. Podstawowymi bytami w
tym świecie powinny być samoloty, statki, porty, lotniska i pasażerowie.

##Wymagania:
- [x] użytkownik tworzy nowe samoloty i statki poprzez panel kontrolny,
- [x] samoloty i statki są tworzone tylko na polecenie użytkownika, pasażerowie pojawiają się automatycznie proporcjonalnie do liczby wycieczkowców i samolotów pasażerskich na mapie,
- [x] stworzony samolot/statek/pasażer powinien mieć automatycznie wylosowane wszystkie wartości pól (nawet jeśli jest tworzony na polecenie użytkownika),
- [x] wszystkie statki i samoloty są rysowane na mapie świata zgodnie z ich aktualnym położeniem,
- [x] każdy pasażer, samolot, statek to osobny wątek,
- [x] użytkownik może oglądać podstawowe informacje o obiekcie (pasażerze, porcie, lotnisku, samolocie, statku) w osobnym oknie lub panelu informacyjnym po kliknięciu na narysowany na mapie obiekt (lub w przypadku pasażerów po wybraniu z listy),
- [x] korzystając z przycisków w oknie informacyjnym obiektu, użytkownik może usunąć samolot/statek z mapy, zmienić trasę statku/samolotu, zmusić samolot do awaryjnego lądowania,
- [x] lotniska mają ograniczoną pojemność; samolot czekający na zwolnienie się miejsca na lotnisku „krąży” wokół lotniska nie tracąc paliwa,
- [x] trasy morskie powinny się krzyżować ze sobą; każda trasa między dwoma portami powinna posiadać co najmniej jedno skrzyżowanie; na skrzyżowaniu tras może się w danej chwili znajdować tylko jeden statek; do zapewnienia bezpieczeństwa na skrzyżowaniu należy wykorzystać mechanizm semaforów lub monitorów,
- [x] trasy lotnicze również powinny się krzyżować ze sobą; każda trasa między dwoma lotniskami powinna posiadać co najmniej jedno skrzyżowanie; na skrzyżowaniu dróg powietrznych może się w danej chwili znajdować tylko jeden samolot; do zapewnienia bezpieczeństwa na skrzyżowaniu proszę wykorzystać mechanizm semaforów lub monitorów,
- [x] wszystkie trasy (zarówno morskie jak i lotnicze) powinny być dwukierunkowe; oba pasy ruchu każdej trasy powinny być graficznie rozdzielone tak aby samoloty/statki podróżujące w przeciwnych kierunkach nie najeżdżały na siebie,
- [x] aplikacja nie ma rozwiązywać żadnych problemów zakleszczeń; jeśli na jakimś skrzyżowaniu wystąpi zakleszczenie, jego rozwiązanie będzie możliwe poprzez usunięcie samolotu/statku z drogi,
- [x] modelowany świat powinien być bezpieczny – żadnych kolizji ani nieudanych postojów,
- [ ] powinna istnieć możliwość zapisania i odtworzenia stanu symulacji poprzez serializację.
