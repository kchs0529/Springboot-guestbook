guestbook

목록 /guestbook/list 		Get 목록/검색/페이징
등록 /guestbook/register	Get 입력화면
       /gusetbook/register	Post 등록처리 		/guestbook/list로 다시돌아가기
조회 /gusetbook/read		GET 조회화면
수정 /gusetbook/modify	GET 수정/삭제기능 화면
       /gusetbook/modify	POST 수정처리 		/gusetbook/read
삭제 /gusetbook/remove	POST 삭제처리		/gusetbook/list

controller 계층 : GusetbookController.java

service 계층 : GusetbookService, GuestbookServiceImpl

repository 계층 : GuestbookRepository
