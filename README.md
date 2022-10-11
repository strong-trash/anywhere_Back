# anywhere_Back
사용된 기술
Spring Framework, Spring Boot, Spring JPA, MYSQL

기능
카테고리, 범위, 현재 위치 좌표 x,y를 입력하여 카카오 API로 부터 
위치를 받아 무작위로 선정하여 위치 정보를 보여줌

게시글 저장, 삭제, 수정 기능

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
1. 랜덤위치 받기
2. 
![ezgif-2-81296ad9bb](https://user-images.githubusercontent.com/70366061/194990486-6dfe6d89-b349-411a-83e1-0fe3379d430a.gif)

![image](https://user-images.githubusercontent.com/70366061/194987182-e6362b1d-c51a-4d38-834a-70ec04f3614d.png)
bigfilter: 추천 받을 카테고리 ex) 공원, 편의점, 문화시설 등
x,y : 현재 위치의 xy 좌표
dis : 현재위치로 부터 탐색할 최대 범위 m 단위이다.
결과값
![image](https://user-images.githubusercontent.com/70366061/194988258-bad4868b-6222-463d-8ce7-fe9cb5589c1b.png)
place_name: 위치 이름
x, y: 무작위 위치의 x,y좌표
addr: 무작위 위치의 주소
article_links: 현재 무작위 위치에 대한 게시글 id 리스트
url: 현 무작위 위치에 대한 카카오 지도 url
info: 현 무작위 위치에 대한 대략적인 정보들

게시글 작성
article_id는 무작위 생성
만약 place 데이터베이스에 현재 무작위 장소에 대한 정보가 없을시 새로 생성후 저장
![image](https://user-images.githubusercontent.com/70366061/194988749-6dba512c-0936-419e-8b05-4db5e24b84ca.png)
![image](https://user-images.githubusercontent.com/70366061/194989248-a8591b8d-b773-4f6f-ab19-405d11ba2df9.png)
![image](https://user-images.githubusercontent.com/70366061/194989445-adc72b1a-945d-4304-8ce1-6749df459858.png)

게시글 수정
![image](https://user-images.githubusercontent.com/70366061/194989580-e33b5622-0022-40fd-93ee-ad88ed5084ff.png)
![image](https://user-images.githubusercontent.com/70366061/194989655-799ce8d6-04a8-41b0-9ef1-f00cd45b070c.png)

게시글 삭제
![image](https://user-images.githubusercontent.com/70366061/194989840-620eefce-87f9-4b07-b917-ffe3f9bf185a.png)
![image](https://user-images.githubusercontent.com/70366061/194989922-b17cff17-9431-44cb-8fce-492d2bcab9da.png)

