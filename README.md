# anywhere_Back
사용된 기술
Spring Framework, Spring Boot, Spring JPA, MYSQL

기능
카카오 API로 부터 랜덤 위치를 받아 위치 정보를 보여줌
게시글 작성, 저장, 삭제, 수정 기능

DTO
Articles
 @Id
  @Column(name="ARTICLE_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long article_id;
  @Column(name="X",nullable = false)
  private double x;
  @Column(name="Y",nullable = false)
  private double y;
  @Column(name="TITLE",length = 255)
  private String title;
  @Column(name="image",nullable = true)
  private byte[] image;
  @Column(name="createdDate",nullable = true)
  private Date createdDate;
  @ManyToOne!

  @JoinColumn(name="Place_id")
  private Places place;
  @Column(name="PASSWORD")
  private String password;
  @Column(name="CONTENT",nullable = true)
  private String content;
 
Places
   @Id
    @Column(name="ADDR")
    private String Place_id;
    @Column
    @OneToMany(mappedBy = "place",cascade = CascadeType.ALL)
    private List<Articles> articles;
 
작동 원리
![그림1](https://user-images.githubusercontent.com/70366061/194986472-c8737be7-69a4-4c8f-acce-a1f37341163e.png)

작동 예시
