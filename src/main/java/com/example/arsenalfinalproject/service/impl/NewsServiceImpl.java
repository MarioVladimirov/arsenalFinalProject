package com.example.arsenalfinalproject.service.impl;

import com.example.arsenalfinalproject.model.binding.NewsAddBindingModel;
import com.example.arsenalfinalproject.model.entity.*;
import com.example.arsenalfinalproject.model.entity.enums.RoleNameEnum;
import com.example.arsenalfinalproject.model.service.NewsAddServiceModel;
import com.example.arsenalfinalproject.model.service.NewsUpdateServiceModel;
import com.example.arsenalfinalproject.model.view.NewsDetailsView;
import com.example.arsenalfinalproject.repository.NewsRepository;
import com.example.arsenalfinalproject.service.CloudinaryService;
import com.example.arsenalfinalproject.service.NewsService;
import com.example.arsenalfinalproject.service.PictureService;
import com.example.arsenalfinalproject.service.UserService;
import com.example.arsenalfinalproject.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final PictureService pictureService;
    private final CloudinaryService cloudinaryService;

    public NewsServiceImpl(NewsRepository newsRepository, ModelMapper modelMapper, UserService userService, PictureService pictureService, CloudinaryService cloudinaryService) {
        this.newsRepository = newsRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.pictureService = pictureService;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void initializeNews() throws IOException {
        if (newsRepository.count() == 0) {

            UserEntity user1 = userService.findUserById(2L).orElseThrow();
            UserEntity admin = userService.findUserById(1L).orElseThrow();

            NewsEntity news1 = new NewsEntity();
            news1.setDescription("Някои твърдят, че футбола е „мъжка“ игра. Оооо, тeзи хора явно никога не са гледали как го играят представителките на „нежния“ пол. Нещо повече, те със сигурност не са били на игрищата на „Грийнспорт“ в столичния квартал Слатина миналата събота. А, трябваше! Защото, така щяха да усетят една истинска страст, неподправена емоция и хъс, които много често липсват в играта на мъжете в тяхната т.н. „мъжка“ игра и които са присъщи само и единствено на „футболните амазонки“. Понякога тези състояния на духа липсват и на нас като привърженици в презадоволената от футбол всяка една съботa! А, съботите са за футбол, но оня истинският, който ни вдъхнови в най-ранна възраст, а после ни събра в организацията „Арсенал България“. Такъв един истински, какъвто се игра на 02 октомври 2021 година от 8-те отбора, които се включиха във вече станалия традиционен турнир за дами любителки. Когато преди няколко години се разгоря един епичен спор между хора от УС и представители на клонове за прословутото точкуване на играещите във Форумната лига и някак еретично се прокрадна идея и за дамски тим, то едва ли и най-смелите мечтатели като Петко Александров(инициатор и треньор на ASCB Ladies) са очаквали, че 4 години по-късно новината за това мероприятие ще стои в спортните медии до имената на Пеп и Клоп. При това напълно заслужено. „В началото бе словото“, както обичаме да си говорим с Петко, защото именно споменатата словесна „дискусия“ (доста меко определение за онзи вербален поток преди години) се роди прекрасната идея да създадем наша любителска дамска футболна формация с първоначален замисъл да подкрепим благотворителна кауза. Това си беше достатъчна мотивация на ентусиазирани момичета да се включат в една непозната за тях област изцяло извън зоната им на комфорт. Още по-приятно за нас е, че магията на играта владее десетки други дами, които вече години наред горят с играта и се включват в нашето скромно мероприятие. Нещо повече, това създаде много нови запознанства и приятелства, а само, който не е гледал състезателния хъс на дамите, не може да разбере с каква страст се хвърлят в двубоите. Именно този интерес към играта и правенето на добро мотивира УС да организира и есенно издание на надпреварата, която смело можем да наречем изключително успешна и гордо да отбележим, как се превърна в част от футболния календар, оня календар облечен в аматьорското начало, но точно онова начало, което води аматьорите до победи над професионалистите. Защото всеки един участвал в организирането, в самите футболни двубои и дарил е истински победител. Един турнир в който големият победител е правенето на добро с любов, каквото е и френското значение на думата „amateur“. В слънчевата събота на 02 октомври в надпреварата се включиха рекордните до момента 8 отбора в лицето на организаторите от ASCB Ladies, ФК „Атлетико“, „EnJoy“, „Лъвиците на Лео“, „Национал НСА“, „PwC“, „Скрити Лимонки“ и „Торнадо лейдис“, както и доброволки дошли с желание да се забавляват и помогнат на нашите партньори от сдружение „Деца с онкохематологични заболявания“. Отборите бяха разпределени в две групи с по 4 състава, като тегленето на жребия и самите срещи бяха транслирани на живо в страницата на АМФЛ във Фейсбук, а това в добавка със съдиите и лекарите накара прекрасните Дами да се почувстват на една истинска и конкурентна футболна арена. По време на турнира видяхме много голове, страхотно индивидуално майсторство, изгаряща енергия, колективен дух и стремеж за отборен резултат. А, когато видиш, как момичета, които в ежедневието носят токчета и елегантни чанти, преминават съперника с финеса на Пирес, стрелят с мощта и точността на Тиери Анри, а в единоборствата са твърди като Адамс и Киоун, то няма как да не изпаднеш в екстаз и да не им отдадеш аплаузи за удоволствието, което доставят. Удоволствие за нас и уважение от вкаралия на Ювентус Екундайо Джайеоба, който също погледа част зрелището на терена. И тук не искаме да говорим за спортни резултати и класиране, а за емоцията на момичетата, за уважението към противника, за извиненията които бяха поднесени при неминуемите инциденти, когато има толкова много емоция и страст. Всички срещи ни припомниха атмосферата на едно вече позабравено очарование във футбола, защото водещата мотивация бе единствено чистосърдечното желание да играят футбол. А, това и липсата на финансови облаги създаде за пореден път една истинска футболна атмосфера, която се превръща в празник за всички нас. Умиление и възхищение предизвиква и това как момичета като Теди от Пловдив и Сандра от Асеновград бяха дошли да играят за честта на ASCB Ladies и в името на една велика кауза, каквато е правенето на добро. Именно доброто и това да научим на добри дела присъстващите на съревнованието деца е и наша цел и призвание, които надхвърлят идеята за един фен клуб в неговия общоприет формат. Идея, която „Арсенал България“ отдавна е превърнал в свое мото. Не би било честно, да не споменем, че EnJoy спечелиха за втори пореден път надпреварата, както и събраните рекордни средства от зрители и партньорите спомоществователи в размер на 13 190,00 лева. Всичко това и усмивките на малките „сървайвари“ са нашата мотивация и катализатор, които ни карат да очакваме с нетърпение следващата надпревара през май 2022 година. От името на ASCB искаме да благодарим на всички наши патньори в лицето на „SiLVER COURT“, „Рейнарс Алуминиум“, фондация „Продължи доброто“ „Eddy’s bakery“, „Еаt &Go“, „Стада България“, „Димиев Студио“, „Скрити лимонки“ и тяхната кампания „Книги за усмивки“, „Веспа Клуб 2% Brothers България“, АМФЛ, спортен комплекс „Грийнспорт“, БНТ и десетките привърженици по трибуните. За всички тях важи в пълна степен и девиза на Дейвид „Роки“ Рокясъл: „Помни кой си, какво си и кого представляваш!“, защото това което те направиха за децата в нужда е това, което ги определя като хора. А, за нас като фен клуб този турнир и издадената автобиография на Иън Райт правят 2021 една забележителна година!");
            news1.setLocalDateNews(LocalDate.parse("2021-09-22"));
            news1.setTopic("Дамите - футбол, вдъхновение, страст и благотворителност!");

            PictureEntity pictureEntity =
                    pictureService.createPictureEntityByPathInit("src/main/resources/static/images/Container/woman.jpg");
            news1.setPicture(pictureEntity);

            news1.setUser(user1);

            NewsEntity news2 = new NewsEntity();
            news2.setDescription("Съставите на Брайтън и Арсенал завършиха при резултат 0:0 в мач от 7 кръг на Висшата лига. С оглед показаното от двата отбора на терена, гостите от Лондон би трябвало да сметнат двубоя като спечелена точка, а не като загубени две. Повечето опасности под проливните дъждове в южна Англия бяха пред вратата на Аарън Рамсдейл, който за пореден път изнесе стабилен двубой и запази мрежата си суха. Това впрочем бе и 3 чиста мрежа за Рамсдейл в 4 мача във Висшата лига. Неговото представяне продължава да бъде на високо ниво и да вдъхва сигурност на отбраната пред него. Домакините от Брайтън затрудниха максимално своят съперник и не му оставиха почти никакви шансове, а с най-чистият такъв се отчете Смит Роу, който можеше да отбележи в края на мача. С тази си спечелена точка, Арсенал вече събра актив от общо 10 и в момента заема 9 позиция във временното класиране. Повече подробности от мача снощи в следващите редове.Думите на Микел Артета след края на двубоя: ''''Мисля, че това е спечелена точка, защото не заслужавахме нищо повече от това. Като цяло се защитавахме на високо ниво в двубоя, особено в заключителните 15-20 минути. Не бих могъл да кажа, обаче, че сме контролирали нещата на терена. Наложи се да положим големи усилия за да пробием пресата на противника. Също така и не разполагахме с чисти пространства по терена като цяло, а това усложни съвсем нещата за нас. Все пак запазихме вратата си суха, а с основен принос за това е Аарън Рамсдейл. Той се представя страхотно и проявява страхотен професионализъм. Освен това, разполага с характер и доказва това, че може да работи под напрежение и то в голям клуб, какъвто е Арсенал.В следващият си двубой, Арсенал приема Кристъл Палас, воден от не кой да е, а Патрик Виейра. Двубоя ще бъде изигран на 18 октомври, понеделник, от 22:00 часа българско време. С това и днешният ни материал завършва. Екипа на Арсенал България ви пожелава приятен завършек на уикенда.");
            news2.setLocalDateNews(LocalDate.parse("2004-01-25"));
            news2.setTopic("Брайтън и Арсенал не излъчиха победител на южния бряг");

            PictureEntity pictureEntity2 =
                    pictureService.createPictureEntityByPathInit("src/main/resources/static/images/Container/brighton.jpg");

            news2.setPicture(pictureEntity2);
            news2.setUser(admin);


            newsRepository.save(news1);
            newsRepository.save(news2);

        }

    }

    @Override
    public NewsDetailsView findById(Long id, String currentUser) {


        return newsRepository
                .findById(id)
                .map(n -> mapDetailsView(currentUser, n))
                .get();
    }

    private NewsDetailsView mapDetailsView(String currentUser, NewsEntity newsEntity) {

        NewsDetailsView newsDetailsView = this.modelMapper.map(newsEntity, NewsDetailsView.class);
        newsDetailsView.setCanDelete(isOwner(currentUser, newsEntity.getId()));
        newsDetailsView.setPublicId(newsEntity.getPicture().getPublicId());

        return newsDetailsView;
    }


    @Override
    public boolean isExistId(Long id) {
        return newsRepository.existsById(id);
    }

    @Override
    public NewsAddServiceModel addNews(NewsAddBindingModel newsAddBindingModel,
                                       String userIdentifier) throws IOException {

        UserEntity userEntity = userService.findByUsername(userIdentifier).orElseThrow();
        NewsAddServiceModel newsAddServiceModel = modelMapper.map(newsAddBindingModel,
                NewsAddServiceModel.class);

        NewsEntity newsEntity = modelMapper.map(newsAddServiceModel, NewsEntity.class);

        newsEntity.setLocalDateNews(LocalDate.now());

        PictureEntity pictureEntity = pictureService.createPictureEntity(newsAddBindingModel.getPicture());

        newsEntity.setPicture(pictureEntity);
        newsEntity.setUser(userEntity);
        NewsEntity savedNews = newsRepository.save(newsEntity);

        return modelMapper.map(savedNews, NewsAddServiceModel.class);
    }

    @Override
    public void deleteProduct(Long id, String publicId) {
        newsRepository.deleteById(id);
        cloudinaryService.delete(publicId);
        pictureService.deletePictureByPublicId(publicId);

    }

    @Override
    public boolean isOwner(String userName, Long id) {

        Optional<NewsEntity> newsOpt = newsRepository
                .findById(id);

        Optional<UserEntity> caller = userService.findByUsername(userName);

        if (newsOpt.isEmpty() || caller.isEmpty()) {
            return false;
        } else {
            NewsEntity newsEntity = newsOpt.get();


            return isAdmin(caller.get()) || newsEntity.getUser().getUsername().equalsIgnoreCase(userName);
        }
    }

    @Override
    public void updateOffer(NewsUpdateServiceModel newsUpdateServiceModel) {

        NewsEntity newsEntity =
                newsRepository.findById(newsUpdateServiceModel.getId()).orElseThrow(() ->
                        new ObjectNotFoundException(newsUpdateServiceModel.getId()));

            newsEntity.setTopic(newsUpdateServiceModel.getTopic());
            newsEntity.setDescription(newsUpdateServiceModel.getDescription());

            newsRepository.save(newsEntity);

    }



    private boolean isAdmin(UserEntity user) {
        return user
                .getRoles()
                .stream()
                .map(RoleEntity::getRole)
                .anyMatch(r -> r == RoleNameEnum.ADMIN);
    }


//    @Override
//    public List<NewsDetailsView> findAllNews() {
//        List<NewsEntity> allNewsEntity = newsRepository.findAll();
//        Collections.reverse(allNewsEntity);
//
//        return
//                allNewsEntity.stream()
//                        .map(n -> {
//                            NewsDetailsView newsDetailsView = modelMapper.map(n, NewsDetailsView.class);
//                            newsDetailsView
//                                    .setPublicId(n.getPicture().getPublicId())
//                                    .setUrlPictureNews(n.getPicture().getUrl());
//
//                            return newsDetailsView;
//                        })
//
//                        .collect(Collectors.toList());
//    }


    @Override
    public Page<NewsDetailsView> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize , Sort.by("id").descending());

        List<NewsEntity> allNewsEntity = newsRepository.findAll();
        Collections.reverse(allNewsEntity);

//        Page<NewsEntity> pageAllNewsDetailsView = this.newsRepository.findAll(pageable, Sort.by(Sort.Direction.ASC,"id"));
//
//        Page<NewsDetailsView> map = pageAllNewsDetailsView.map(n -> {
//            NewsDetailsView newsDetailsView = modelMapper.map(n, NewsDetailsView.class);
//            newsDetailsView
//                    .setPublicId(n.getPicture().getPublicId())
//                    .setUrlPictureNews(n.getPicture().getUrl());
//            return newsDetailsView;
//        });

        return         this.newsRepository.findAll(pageable)
                    .map(n -> {
            NewsDetailsView newsDetailsView = modelMapper.map(n, NewsDetailsView.class);
            newsDetailsView
                    .setPublicId(n.getPicture().getPublicId())
                    .setUrlPictureNews(n.getPicture().getUrl());
            return newsDetailsView;
                    });

    }






}
