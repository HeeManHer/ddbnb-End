# 백엔드 서버

- 샘플을 만들긴 했습니다. 참고해서 만드세요
- 기본 패키지는 com.nasigolang.ddbnb입니다. 혹시 오타가 있다면 참으세요.
- common과 config는 건들지 마세요. 무슨일이 일어날지 모릅니다.
- 롬복을 설치했습니다.이제 DTO를 편하게 만드세요.
    - 샘플 DTO를 확인해보세요.
    - 그래도 사용방법을 모른다면 인터넷을 검색하세요. 친절하게 알려 줄것입니다.
- 혹시 사용상에 문제가 있다면 알아서 해결하십시요. GPT는 우리의 적이 아닙니다.

# JPA 사용법

## 기본

- 나중에 알려줄게

### 쿼리메소드 유형

     * And                  ex) findByMenuCodeAndMenuName                   -> where x.menuCode =?1 and x.menuName =?2
     * Or                   ex) findByMenuCodeOrMenuName                    -> where x.menuCode =?1 or x.menuName =?2
     * Between              ex) findByMenuPriceBetween                      -> where x.menuPrice between ?1 and ?2
     * LessThan             ex) findByMenuPriceLessThan                     -> where x.menuPrice < ?1
     * LessThanEqual        ex) findByMenuPriceLessThanEqual                -> where x.menuPrice <= ?1
     * GraterThan           ex) findByMenuPriceGraterThan                   -> where x.menuPrice > ?1
     * GraterThanEqual      ex) findByMenuPriceGraterThanEqual              -> where x.menuPrice >= ?1
     * After                ex) findByDateAfter                             -> where x.date > ?1
     * Before               ex) findByDateBefore                            -> where x.date < ?1
     * IsNUll               ex) findByMenuNameIsNull                        ->  where x.menuName is null
     * IsNotNull, NotNull   ex) findByMenuName(Is)NotNull                   ->  where x.menuName is not null
     * Like                 ex) findByMenuNameLike                          ->  where x.menuName like ?1
     * NotLike              ex) findByMenuNameNotLike                       ->  where x.menuName not like ?1
     * StaringWith          ex) findByMenuNameStaringWith                   ->  where x.menuName like ?1 || '%'
     * EndingWith           ex) findByMenuNameEndingWith                    ->  where x.menuName like '%' || ?1
     * Containing           ex) findByMenuNameContaining                    ->  where x.menuName like '%' || ?1 || '%'
     * OrderBy              ex) findByPriceOrderByMenuCodeDesc              -> where x.menuPrice = ?1 order by x.menuCode desc
     * Not                  ex) findByMenuNameNot                           -> where x.menuName <> ?1
     * In                   ex) findByMenuNameIn(Collection<Name> names)    -> where x.menuName in (?1)
