# Javni gradski prijevoz

## Pokretanje

Potreban je Maven.

```bash
mvn spring-boot:run
```

Za bazu se koristi H2 baza koja se automatski pokreće.

Inicijalizaciju baze provodi razdred [Init.java](/src/main/java/com/example/jgp/db_init/Init.java).

## Testiranje

```bash
mvn test
```