## 2025-07-25
- @Transactional(readOnly = true) : 읽기 전용 트랜잭션, 성능 최적화 용도
- @ManyToOne(fetch = FetchType.LAZY) : 지연 로딩 설정, 실제 접근 시에만 SELECT 발생
- record 타입 : 불변 DTO용 간단 문법, JPA Entity에는 부적합
- @Query + DTO 반환 시 new DTO(...) 생성자 표현식 필수
- 제네릭 <T, E> : 역할을 나눠서 타입을 유연하게 받기 위한 구조
- orphanRemoval = true : 부모 컬렉션에서 제거하면 자식 엔티티도 자동 삭제됨 (외래키 있어도 가능)