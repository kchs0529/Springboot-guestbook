package org.zerock.guestbook.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.guestbook.entity.Guestbook;
import org.zerock.guestbook.entity.QGuestbook;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTests {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1,300).forEach(i->{
            Guestbook guestbook = Guestbook.builder()
                    .title("Title......"+i)
                    .content("Content........"+i)
                    .writer("user"+(i%10))
                    .build();
            System.out.println(guestbookRepository.save(guestbook));
        });
    }

    @Test
    public void updateTest(){

        Optional<Guestbook> result = guestbookRepository.findById(900L);
        if(result.isPresent()){
            Guestbook guestbook = result.get();
            guestbook.changeTitle("Changed Title....");
            guestbook.changeContent("Changed Content.....");
            guestbookRepository.save(guestbook);
        }
    }

    @Test
    public void testQuery1(){//제목에 1이있는애만

        Pageable pageable = PageRequest.of(1,10, Sort.by("gno").descending());
        //1.BooleanBuilder를 생성
        QGuestbook qGuestbook = QGuestbook.guestbook; //q도메인정보를사용할수있다.
        String keyword = "1";
        BooleanBuilder builder = new BooleanBuilder();

        //2.조건에 맞는 구문은 Querydsl에서 사용하는 조건타입의 함수를 생성
        BooleanExpression expression = qGuestbook.title.contains(keyword); //title이 keyword를 포함하고있는 조건생성
        builder.and(expression); // 조건을 빌더에 붙여서 집어넣었다.

        //3.BooleanBuilder에 작성한 조건을 추가하고 실행한다.
        Page<Guestbook> result = guestbookRepository.findAll(builder,pageable);
        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }

    @Test
    public void testQuery2(){ //제목,내용에 1이 있는애만
        Pageable pageable = PageRequest.of(1,10, Sort.by("gno").descending());
        QGuestbook qGuestbook = QGuestbook.guestbook;

        BooleanBuilder builder = new BooleanBuilder();
        String keyword = "1";
        BooleanExpression exTitle = qGuestbook.title.contains(keyword);
        BooleanExpression exContent = qGuestbook.content.contains(keyword);
        BooleanExpression exAll =  exTitle.or(exContent);
        builder.and(exAll);
        builder.and(qGuestbook.gno.gt(0L));//gno가 0이상인 애

        //where like .. or like ..
        //and gno > 0;

        Page<Guestbook> result = guestbookRepository.findAll(builder,pageable);
        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }

}
