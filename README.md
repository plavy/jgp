# Javni gradski prijevoz

## Pokretanje

Potreban je Maven.

```bash
mvn spring-boot:run
```

Za bazu se koristi H2 baza koja se automatski pokreće.

Inicijalizaciju baze provodi razdred [Init.java](/src/main/java/com/example/jgp/db_init/Init.java).

Aplikacija je lokalno dostupna na http://localhost:8000.

Baza podataka je dostupna na http://localhost:8000/h2-console.

Camunda platforma je dostupna na http://localhost:8000/camunda.

## Testiranje

```bash
mvn test
```