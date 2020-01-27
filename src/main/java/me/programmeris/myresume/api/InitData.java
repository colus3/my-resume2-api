package me.programmeris.myresume.api;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.dto.Password;
import me.programmeris.myresume.api.entity.base.Address;
import me.programmeris.myresume.api.entity.content.Content;
import me.programmeris.myresume.api.entity.content.Tag;
import me.programmeris.myresume.api.entity.content.item.ContentItem;
import me.programmeris.myresume.api.entity.content.item.Education;
import me.programmeris.myresume.api.entity.content.item.Interest;
import me.programmeris.myresume.api.entity.content.item.Profile;
import me.programmeris.myresume.api.entity.resume.*;
import me.programmeris.myresume.api.entity.user.User;
import me.programmeris.myresume.api.repository.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static me.programmeris.myresume.api.entity.content.ContentType.*;

@Component
@RequiredArgsConstructor
public class InitData {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.doInitTags();
        initService.doInitValues();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final UserRepository userRepository;
        private final ResumeRepository resumeRepository;
        private final ContentRepository contentRepository;
        private final TagRepository tagRepository;
        private final ResumeContentItemRepository resumeContentItemRepository;

        public void doInitTags() {

            List<String> tags = Lists.newArrayList(
                    "Java",
                    "Scala",
                    "C/C++",
                    "C#",
                    "Java8",
                    "Swift",
                    "NodeJs",
                    "ReactJs",
                    "Python",
                    "Spring",
                    "Oracle",
                    "Altibase",
                    "MSSql",
                    "MySql",
                    "Redis",
                    "MongoDB",
                    "OrientDB",
                    "SNMP",
                    "RespberryPi",
                    "Nginx",
                    "cUrl",
                    "Socket",
                    "GZip"
            );

            tags.stream().map(Tag::new).forEach(tagRepository::save);
        }

        public void doInitValues() {
            /* 사용자 생성 */
            User user = createUser("Donghwan Lee", "+82-10-2041-9909", "colus4@gmail.com");
            user.setPassword(new Password("1234").get());
            user.setBirthDate(LocalDate.of(1982, 1, 26));
            user.setAddress(new Address("", "경기도 고양시", "일산서구 주엽동"));
            userRepository.save(user);

            /* 이력서 생성 */
            Resume resume = createResume(ResumeType.TYPE_1, "Default", "Y", user);
            resumeRepository.save(resume);

            /* Profile 콘텐츠 생성 */
            Content profiles = createContent(1L,
                                             PROFILE,
                                             "MyProfile",
                                             user);
            contentRepository.save(profiles);

            /* Profile 등록 */
            Profile myProfile = createProfile("나는 프로그래머다", "난 12년된 프로그래머다. 뭐든 할 수 있다.");
            profiles.addContentItem(myProfile);

            /* 이력서에 Profile 콘텐츠 등록 */
            ResumeContent profileContent = createResumeContent("My Profile",
                                                               Position.LEFT);
            profileContent.setContent(profiles);

            resumeRepository.flush();

            /* 이력서 콘텐츠에도 별도로 추가 */
            ResumeContentItem profileResumeContentItem = createResumeContentItem(myProfile);

            resume.addResumeContents(profileContent, profileResumeContentItem);

            /* Education 콘텐츠 생성 */
            Content educations = createContent(2L,
                                               EDUCATION,
                                               "MyEducation",
                                               user);
            contentRepository.save(educations);

            /* Education 1 생성 */
            Education education1 = createEducation(LocalDateTime.of(2015, 3, 10, 0, 0),
                                                   LocalDateTime.of(2015, 4, 10, 0, 0),
                                                   "교육 이수");

            /* Education 2 생성 */
            Education education2 = createEducation(LocalDateTime.of(2000, 3, 1, 0, 0),
                                                   LocalDateTime.of(2008, 2, 28, 0, 0),
                                                   "대학 졸업");
            educations.addContentItem(education1, education2);

            /* 이력서에 Education 콘텐츠 등록 */
            ResumeContent educationContent = createResumeContent("My Education",
                                                                 Position.RIGHT);
            educationContent.setContent(educations);

            resumeRepository.flush();

            /* 이력서 콘텐츠에도 별도로 추가 */
            ResumeContentItem education2ResumeContentItem = createResumeContentItem(education2);

            resume.addResumeContents(educationContent, education2ResumeContentItem);

            /* Interest 콘텐츠 생성 */
            Content interests = createContent(3L,
                    INTEREST,
                    "MyInterest",
                    user);
            contentRepository.save(interests);

            /* Interest 생성 */
            Interest java = createInterest("Java");
            Interest java8 = createInterest("Java8");
            interests.addContentItem(java, java8);

            /* 이력서에 Interest 콘텐츠 등록 */
            ResumeContent interestContent = createResumeContent("My Interest",
                    Position.LEFT);

            interestContent.setContent(interests);

            resumeRepository.flush();

            /* 이력서 콘텐츠에도 별도로 추가 */
            ResumeContentItem javaResumeContentItem = createResumeContentItem(java);
            ResumeContentItem java8ResumeContentItem = createResumeContentItem(java8);

            resume.addResumeContents(interestContent, javaResumeContentItem, java8ResumeContentItem);
        }

        private Interest createInterest(String tagName) {
            Interest interest = new Interest();
            Tag tag = tagRepository.findByName(tagName);
            interest.setTag(tag);
            return interest;
        }

        private Education createEducation(LocalDateTime startDt, LocalDateTime endDt, String contents) {
            Education education1 = new Education();
            education1.setContents(contents);
            education1.setStartDt(startDt);
            education1.setEndDt(endDt);
            return education1;
        }

        private ResumeContent createResumeContent(String displayName, Position position) {
            ResumeContent profileContent = new ResumeContent();
            profileContent.setDisplayName(displayName);
            profileContent.setPosition(position);
            return profileContent;
        }

        private Profile createProfile(String title, String contents) {
            Profile myProfile = new Profile();
            myProfile.setTitle(title);
            myProfile.setContents(contents);
            return myProfile;
        }

        private Content createContent(Long displayOrder,
                                      String contentType,
                                      String contentName,
                                      User user) {
            Content content = new Content();
            content.setDisplayOrder(displayOrder);
            content.setType(contentType);
            content.setName(contentName);
            content.setCreateUserId(user.getId());
            content.setUpdateUserId(user.getId());
            content.setUser(user);
            return content;
        }

        private Resume createResume(ResumeType resumeType, String resumeName, String useYn, User user) {
            Resume resume = new Resume();
            resume.setResumeType(resumeType);
            resume.setResumeName(resumeName);
            resume.setUser(user);
            resume.setUseYn(useYn);
            resume.setCreateUserId(user.getId());
            resume.setUpdateUserId(user.getId());
            resume.setDirectAccessId("69586509-155a-483f-a9b0-71dfa9dcce28");
            return resume;
        }

        private User createUser(String username, String phone, String email) {
            User user = new User();
            user.setUsername(username);
            user.setPhone(phone);
            user.setEmail(email);
            user.setCreateUserId(1L);
            user.setCreateDt(LocalDateTime.now());
            user.setUpdateUserId(1L);
            user.setUpdateDt(LocalDateTime.now());
            return user;
        }

        private <T extends ContentItem> ResumeContentItem createResumeContentItem(T item) {
            ResumeContentItem resumeContentItem = new ResumeContentItem();
            resumeContentItem.setContentItem(item);

            return resumeContentItem;
        }
    }
}
