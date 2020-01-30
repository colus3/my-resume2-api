package me.programmeris.myresume.api;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.dto.Password;
import me.programmeris.myresume.api.entity.base.Address;
import me.programmeris.myresume.api.entity.content.Content;
import me.programmeris.myresume.api.entity.content.Tag;
import me.programmeris.myresume.api.entity.content.item.*;
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
            User user = initUser();

            /* Profile 콘텐츠 생성 */
            Content profiles = initProfile(user);

            /* Education 콘텐츠 생성 */
            Content educations = initEducation(user);

            /* Certification 콘텐츠 생성 */
            Content certifications = initCertification(user);

            /* Interest 콘텐츠 생성 */
            Content interests = initInterest(user);

            /* Skill 콘텐츠 생성 */
            Content skills = initSkill(user);

            /* 근무 이력 콘텐츠 생성 */
            Content workExp = initWorkExp(user);

            /* 이력서 생성 */
            initResume(user, profiles, educations, certifications, interests, skills, workExp);
        }

        private void initResume(User user, Content profiles, Content educations, Content certifications, Content interests, Content skills, Content workExp) {

            Resume resume = createResume(ResumeType.TYPE_1, "Default", "프로그래밍을 좋아하는 개발자", "Y", user);
            resumeRepository.save(resume);

            /* 이력서에 Profile 콘텐츠 등록 */
            ResumeContent profileContent = createResumeContent("My Profile",
                                                               Position.RIGHT);
            profileContent.setContent(profiles);

            resumeRepository.flush();

            /* 이력서 콘텐츠에도 별도로 추가 */
            ResumeContentItem profileResumeContentItem = createResumeContentItem(profiles.getContentItems().get(0));
            resume.addResumeContents(profileContent, profileResumeContentItem);


            /* 이력서에 Education 콘텐츠 등록 */
            ResumeContent educationContent = createResumeContent("My Education",
                                                                 Position.LEFT);
            educationContent.setContent(educations);

            resumeRepository.flush();

            /* 이력서 콘텐츠에도 별도로 추가 */
            ResumeContentItem educationResumeContentItem = createResumeContentItem(educations.getContentItems().get(0));
            resume.addResumeContents(educationContent, educationResumeContentItem);

            /* 이력서에 Certification 콘텐츠 등록 */
            ResumeContent certificationContent = createResumeContent("My Certification", Position.LEFT);
            certificationContent.setContent(certifications);

            resumeRepository.flush();

            /* 이력서 콘텐츠에도 별도로 추가 */
            ResumeContentItem certificationResumeContentItem1 = createResumeContentItem(certifications.getContentItems().get(0));
            ResumeContentItem certificationResumeContentItem2 = createResumeContentItem(certifications.getContentItems().get(1));
            ResumeContentItem certificationResumeContentItem4 = createResumeContentItem(certifications.getContentItems().get(3));
            resume.addResumeContents(certificationContent,
                    certificationResumeContentItem1,
                    certificationResumeContentItem2,
                    certificationResumeContentItem4);

            /* 이력서에 Interest 콘텐츠 등록 */
            ResumeContent interestContent = createResumeContent("My Interest", Position.LEFT);
            interestContent.setContent(interests);

            resumeRepository.flush();

            /* 이력서 콘텐츠에도 별도로 추가 */
            ResumeContentItem interestResumeContentItem1 = createResumeContentItem(interests.getContentItems().get(0));
            ResumeContentItem interestResumeContentItem2 = createResumeContentItem(interests.getContentItems().get(1));
            resume.addResumeContents(interestContent, interestResumeContentItem1, interestResumeContentItem2);

            /* 이력서에 Skill 콘텐츠 등록 */
            ResumeContent skillContent = createResumeContent("My Skill",
                    Position.LEFT);
            skillContent.setContent(skills);

            resumeRepository.flush();

            /* 이력서 콘텐츠에도 별도로 추가 */
            ResumeContentItem skillResumeContentItem1 = createResumeContentItem(skills.getContentItems().get(0));
            ResumeContentItem skillResumeContentItem2 = createResumeContentItem(skills.getContentItems().get(1));
            ResumeContentItem skillResumeContentItem3 = createResumeContentItem(skills.getContentItems().get(2));
            ResumeContentItem skillResumeContentItem4 = createResumeContentItem(skills.getContentItems().get(3));

            resume.addResumeContents(skillContent,
                    skillResumeContentItem1,
                    skillResumeContentItem2,
                    skillResumeContentItem3,
                    skillResumeContentItem4);

            /* 이력서에 근무 이력 콘텐츠 등록 */
            ResumeContent workExpContent = createResumeContent("My WorkExperience", Position.RIGHT);
            workExpContent.setContent(workExp);

            resumeRepository.flush();

            /* 이력서 콘텐츠에도 별도로 추가 */
            ResumeContentItem workExpResumeContentItem1 = createResumeContentItem(workExp.getContentItems().get(0));
            ResumeContentItem workExpResumeContentItem2 = createResumeContentItem(workExp.getContentItems().get(1));
            resume.addResumeContents(workExpContent,
                    workExpResumeContentItem1,
                    workExpResumeContentItem2);
        }

        private Content initWorkExp(User user) {
            Content workExp = createContent(6L,
                    WORK_EXPERIENCE,
                    "My Work Experience",
                    user);
            contentRepository.save(workExp);

            /* 근무 이력 생성 */
            WorkExperience vsquare = createWorkExp("VSQUARE", "* 전회사",
                    LocalDateTime.of(2018, 5, 1, 0, 0),
                    LocalDateTime.of(2019, 9, 15, 23, 59),
                    Lists.newArrayList("Java", "Spring", "JPA", "MySQL", "Nginx"));

            WorkExperience codebean = createWorkExp("CODEBean", "* 전전회사",
                    LocalDateTime.of(2015, 5, 1, 0, 0),
                    LocalDateTime.of(2018, 9, 15, 23, 59),
                    Lists.newArrayList("Java", "Spring", "MyBatis", "MySQL", "Oracle", "Python", "Swift"));

            workExp.addContentItem(vsquare, codebean);
            return workExp;
        }

        private Content initSkill(User user) {
            Content skills = createContent(5L,
                    SKILL,
                    "MySkill",
                    user);
            contentRepository.save(skills);

            /* Skill 생성 */
            Skill javaSkill = createSkill("Java", 100.0);
            Skill c_cppSkill = createSkill("C/C++", 80.0);
            Skill mySqlSkill = createSkill("MySql", 70.0);
            Skill oracleSkill = createSkill("Oracle", 70.0);

            skills.addContentItem(javaSkill, c_cppSkill, mySqlSkill, oracleSkill);
            return skills;
        }

        private Content initInterest(User user) {
            Content interests = createContent(4L,
                    INTEREST,
                    "MyInterest",
                    user);
            contentRepository.save(interests);

            /* Interest 생성 */
            Interest java = createInterest("Java");
            Interest java8 = createInterest("Java8");
            interests.addContentItem(java, java8);
            return interests;
        }

        private Content initCertification(User user) {
            Content certifications = createContent(3L, CERTIFICATION, "MyCertification", user);
            contentRepository.save(certifications);

            Certification certification1 = createCertification(
                    LocalDateTime.of(2002, 5, 1, 0, 0), "Microsoft Certified Professional (MCP)");

            Certification certification2 = createCertification(
                    LocalDateTime.of(2003, 7, 1, 0, 0), "Sun Certified Java Professional (SCJP)");

            Certification certification3 = createCertification(
                    LocalDateTime.of(2002, 8, 1, 0, 0), "정보처리산업기사");

            Certification certification4 = createCertification(
                    LocalDateTime.of(2007, 6, 1, 0, 0), "정보처리기사");

            certifications.addContentItem(certification1, certification2, certification3, certification4);
            return certifications;
        }

        private Content initEducation(User user) {
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
            return educations;
        }

        private Content initProfile(User user) {
            Content profiles = createContent(1L,
                                             PROFILE,
                                             "MyProfile",
                                             user);
            contentRepository.save(profiles);

            /* Profile 등록 */
            Profile myProfile = createProfile("나는 프로그래머다", "난 12년된 프로그래머다. 뭐든 할 수 있다.");
            profiles.addContentItem(myProfile);
            return profiles;
        }

        private User initUser() {
            User user = createUser("Donghwan Lee", "+82-10-2041-9909", "colus4@gmail.com");
            user.setPassword(new Password("1234").get());
            user.setBirthDate(LocalDate.of(1982, 1, 26));
            user.setAddress(new Address("", "경기도 고양시", "일산서구 주엽동"));
            userRepository.save(user);
            return user;
        }

        private WorkExperience createWorkExp(String name, String description, LocalDateTime startDt, LocalDateTime endDt, List<String> tagNames) {
            WorkExperience workExperience = new WorkExperience();
            List<Tag> tags = tagRepository.findAllByNameIn(tagNames);
            workExperience.setTitle(name);
            workExperience.setContents(description);
            workExperience.setStartDt(startDt);
            workExperience.setEndDt(endDt);
            workExperience.setTags(tags);

            return workExperience;
        }

        private Skill createSkill(String tagName, Double point) {
            Skill skill = new Skill();
            Tag tag = tagRepository.findByName(tagName);
            skill.setTags(Lists.newArrayList(tag));
            skill.setPoint(point);
            return skill;
        }

        private Interest createInterest(String tagName) {
            Interest interest = new Interest();
            Tag tag = tagRepository.findByName(tagName);
            interest.setTags(Lists.newArrayList(tag));
            return interest;
        }

        private Certification createCertification(LocalDateTime startDt, String contents) {
            Certification certification = new Certification();
            certification.setContents(contents);
            certification.setStartDt(startDt);
            return certification;
        }

        private Education createEducation(LocalDateTime startDt, LocalDateTime endDt, String contents) {
            Education education = new Education();
            education.setContents(contents);
            education.setStartDt(startDt);
            education.setEndDt(endDt);
            return education;
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

        private Resume createResume(ResumeType type, String name, String shortIntro, String useYn, User user) {
            Resume resume = new Resume();
            resume.setType(type);
            resume.setName(name);
            resume.setShortIntro(shortIntro);
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
