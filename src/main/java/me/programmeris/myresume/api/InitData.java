package me.programmeris.myresume.api;

import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.entity.content.Content;
import me.programmeris.myresume.api.entity.content.item.Education;
import me.programmeris.myresume.api.entity.content.item.Profile;
import me.programmeris.myresume.api.entity.resume.Position;
import me.programmeris.myresume.api.entity.resume.Resume;
import me.programmeris.myresume.api.entity.resume.ResumeContent;
import me.programmeris.myresume.api.entity.resume.ResumeType;
import me.programmeris.myresume.api.entity.user.User;
import me.programmeris.myresume.api.repository.ContentRepository;
import me.programmeris.myresume.api.repository.ResumeRepository;
import me.programmeris.myresume.api.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

import static me.programmeris.myresume.api.entity.content.ContentType.EDUCATION;
import static me.programmeris.myresume.api.entity.content.ContentType.PROFILE;

@Component
@RequiredArgsConstructor
public class InitData {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.doInit();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final UserRepository userRepository;
        private final ResumeRepository resumeRepository;
        private final ContentRepository contentRepository;

        public void doInit() {
            User user = createUser("Donghwan Lee", "010-2041-9909", "colus4@gmail.com");
            userRepository.save(user);

            Resume resume = createResume(ResumeType.TYPE_1, "Default", "Y", user);
            resumeRepository.save(resume);

            Content profiles = createContent(1L,
                                             PROFILE,
                                             "MyProfile",
                                             user);
            contentRepository.save(profiles);

            Profile myProfile = createProfile("나는 프로그래머다", "난 12년된 프로그래머다. 뭐든 할 수 있다.");
            myProfile.setContent(profiles);
            profiles.addContentItem(myProfile);

            ResumeContent profileContent = createResumeContent("My Profile",
                                                               Position.LEFT);
            profileContent.setResume(resume);
            profileContent.setContent(profiles);

            resume.addResumeContents(profileContent);

            Content educations = createContent(2L,
                                               EDUCATION,
                                               "MyEducation",
                                               user);
            contentRepository.save(educations);

            Education education1 = createEducation(LocalDateTime.of(2015, 3, 10, 0, 0),
                                                   LocalDateTime.of(2015, 4, 10, 0, 0),
                                                   "교육 이수");
            education1.setContent(educations);
            educations.addContentItem(education1);

            Education education2 = createEducation(LocalDateTime.of(2000, 3, 1, 0, 0),
                                                   LocalDateTime.of(2008, 2, 28, 0, 0),
                                                   "대학 졸업");
            education2.setContent(educations);
            educations.addContentItem(education2);

            ResumeContent educationContent = createResumeContent("My Education",
                                                                 Position.RIGHT);
            educationContent.setContent(educations);
            educationContent.setResume(resume);

            resume.addResumeContents(educationContent);
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
    }
}
