package com.example.arsenalfinalproject.service.impl;

import com.example.arsenalfinalproject.model.entity.MemberTopicEntity;
import com.example.arsenalfinalproject.repository.MemberTopicRepository;
import com.example.arsenalfinalproject.service.MemberTopicService;
import org.springframework.stereotype.Service;

@Service
public class MemberTopicServiceImpl implements MemberTopicService {

    private final MemberTopicRepository memberTopicRepository;

    public MemberTopicServiceImpl(MemberTopicRepository memberTopicRepository) {
        this.memberTopicRepository = memberTopicRepository;
    }


    @Override
    public void initializeTopicMember() {

        if (memberTopicRepository.count() == 0) {
            MemberTopicEntity firstMember = new MemberTopicEntity();
            firstMember.setDescription("Това е първото гостуване на ФК Арсенал в България. 2100 привърженици на \"Арсенал\" изпълниха гостуващият сектор на Националния стадион и над 200 члена на фенклуба бяха там.");
            firstMember.setTitle("Арсенал в България");
            firstMember.setUrlTopicPicture("https://res.cloudinary.com/mariovl/image/upload/v1635955888/membership_topic/ludo_rmhtrv.jpg");

            MemberTopicEntity secondMember = new MemberTopicEntity();
            secondMember.setDescription("Последното групово гостуване на фенклуба на Арсенал-България преди пандемията.Групата от България беше над 150 члена на Фенклуб Арсенал-България.");
            secondMember.setTitle("Олимпиакос - Арсенал");
            secondMember.setUrlTopicPicture("https://res.cloudinary.com/mariovl/image/upload/v1635955889/membership_topic/olymp_rtlz2x.jpg");

            MemberTopicEntity thirtyMember = new MemberTopicEntity();
            thirtyMember.setDescription("Снимка изпратена изпратена от член на фенклуба. Разказа ,че това е най-невероятното нещо което му се е случвало.");
            thirtyMember.setTitle("Арсенал - Тотнъм");
            thirtyMember.setUrlTopicPicture("https://res.cloudinary.com/mariovl/image/upload/v1635955888/membership_topic/totn_agsydw.jpg");

            memberTopicRepository.save(firstMember);
            memberTopicRepository.save(secondMember);
            memberTopicRepository.save(thirtyMember);
        }
    }


}
