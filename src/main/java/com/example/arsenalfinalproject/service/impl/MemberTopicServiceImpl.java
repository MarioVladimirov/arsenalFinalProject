package com.example.arsenalfinalproject.service.impl;

import com.example.arsenalfinalproject.model.entity.MemberTopicEntity;
import com.example.arsenalfinalproject.model.entity.PictureEntity;
import com.example.arsenalfinalproject.model.entity.UserEntity;
import com.example.arsenalfinalproject.model.service.TopicHistoryServiceModel;
import com.example.arsenalfinalproject.model.view.MemberTopicView;
import com.example.arsenalfinalproject.repository.MemberTopicRepository;
import com.example.arsenalfinalproject.service.MemberTopicService;
import com.example.arsenalfinalproject.service.PictureService;
import com.example.arsenalfinalproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberTopicServiceImpl implements MemberTopicService {

    private final MemberTopicRepository memberTopicRepository;
    private final UserService userService;
    private final PictureService pictureService;
    private final ModelMapper modelMapper;


    public MemberTopicServiceImpl(MemberTopicRepository memberTopicRepository, UserService userService, PictureService pictureService, ModelMapper modelMapper) {
        this.memberTopicRepository = memberTopicRepository;
        this.userService = userService;
        this.pictureService = pictureService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void initializeTopicMember() throws IOException {

        if (memberTopicRepository.count() == 0) {

            UserEntity user1 = userService.findUserById(2L).orElseThrow();
            UserEntity ivan87 = userService.findUserById(3L).orElseThrow();


            MemberTopicEntity firstMember = new MemberTopicEntity();
            firstMember.setDescription("Това е първото гостуване на ФК Арсенал в България. 2100 привърженици на \"Арсенал\" изпълниха гостуващият сектор на Националния стадион и над 200 члена на фенклуба бяха там.");
            firstMember.setTitle("Арсенал в България");
            firstMember.setApproved(true);

            PictureEntity pictureEntity =
                    pictureService.createPictureEntityByPathInit("src/main/resources/static/images/membership/ludo.JPG");

            firstMember.setPicture(pictureEntity);
            firstMember.setUser(user1);


            MemberTopicEntity secondMember = new MemberTopicEntity();
            secondMember.setDescription("Последното групово гостуване на фенклуба на Арсенал-България преди пандемията.Групата от България беше над 150 члена на Фенклуб Арсенал-България.");
            secondMember.setTitle("Олимпиакос - Арсенал");

            PictureEntity pictureEntity2 =
                    pictureService.createPictureEntityByPathInit("src/main/resources/static/images/membership/olymp.jpg");

            secondMember.setPicture(pictureEntity2);
            secondMember.setUser(ivan87);
            secondMember.setApproved(true);

            MemberTopicEntity thirtyMember = new MemberTopicEntity();
            thirtyMember.setDescription("Снимка изпратена изпратена от член на фенклуба. Разказа ,че това е най-невероятното нещо което му се е случвало.");
            thirtyMember.setTitle("Арсенал - Тотнъм");

            PictureEntity pictureEntity3 =
                    pictureService.createPictureEntityByPathInit("src/main/resources/static/images/membership/totn.JPG");

            thirtyMember.setPicture(pictureEntity3);
            thirtyMember.setUser(user1);
            thirtyMember.setApproved(true);

            MemberTopicEntity fourMember = new MemberTopicEntity();
            fourMember.setDescription("Това е първият ми мач на живо ");
            fourMember.setTitle("Арсенал - Евертън");

            PictureEntity pictureEntity4 =
                    pictureService.createPictureEntityByPathInit("src/main/resources/static/images/membership/everton.jpg");

            fourMember.setPicture(pictureEntity4);
            fourMember.setUser(ivan87);
            fourMember.setApproved(true);


            memberTopicRepository.save(firstMember);
            memberTopicRepository.save(secondMember);
            memberTopicRepository.save(thirtyMember);
            memberTopicRepository.save(fourMember);
        }
    }

    @Override
    public List<MemberTopicView> getMemberTopicAllLimit3() {

        List<MemberTopicEntity> memberTopicEntitiesOnlyApproved = memberTopicRepository.getAllByApprovedTrue();

        Collections.shuffle(memberTopicEntitiesOnlyApproved);

        int maxTopicView = 3;

        List<MemberTopicEntity> viewTopicList = memberTopicEntitiesOnlyApproved.subList(0, maxTopicView);

        return viewTopicList.
                stream().
                map(memberTopicEntity -> {

                    MemberTopicView memberTopicView = new MemberTopicView();
                    memberTopicView.
                            setUsername(memberTopicEntity.getUser().getUsername()).
                            setTitle(memberTopicEntity.getTitle()).
                            setUrlPicture(memberTopicEntity.getPicture().getUrl()).
                            setDescription(memberTopicEntity.getDescription());
                    return memberTopicView;
                }).collect(Collectors.toList());
    }

    @Override
    public void addTopicHistoryUser(TopicHistoryServiceModel topicHistoryServiceModel, String currentUser) throws IOException {

        UserEntity userEntity = userService.findByUsername(currentUser).orElseThrow();

        MemberTopicEntity memberTopicEntity = modelMapper.map(topicHistoryServiceModel , MemberTopicEntity.class);

        PictureEntity pictureEntity = pictureService.createPictureEntity(topicHistoryServiceModel.getPicture());

        memberTopicEntity.
                setUser(userEntity).
                setPicture(pictureEntity).
                setApproved(false);

        memberTopicRepository.save(memberTopicEntity);

    }
}
