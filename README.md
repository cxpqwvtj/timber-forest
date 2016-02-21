# timber-forest

- 環境別のapplicationファイル切り替え

-- application-local.yml.template をコピーして、application-local.yml を置く

-- 起動パラメータで切り替え

```
--spring.profiles.active=local
```

-- 環境変数で切り替え

```
SPRING_PROFILES_ACTIVE=local
```
