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

            Resume resume = new Resume();
            resume.setResumeType(ResumeType.TYPE_1);
            resume.setResumeName("Default");
            resume.setUser(user);
            resume.setUseYn("Y");
            resume.setCreateUserId(user.getId());
            resume.setUpdateUserId(user.getId());
            resumeRepository.save(resume);

            Content profiles = new Content();
            profiles.setDisplayOrder(1L);
            profiles.setType(PROFILE);
            profiles.setName("MyProfile");
            profiles.setCreateUserId(user.getId());
            profiles.setUpdateUserId(user.getId());

            Profile myProfile = new Profile();
            myProfile.setTitle("나는 프로그래머다");
            myProfile.setContent(profiles);
            myProfile.setContents("난 12년된 프로그래머다. 뭐든 할 수 있다.");
            profiles.addContentItem(myProfile);
            contentRepository.save(profiles);

            ResumeContent profileContent = new ResumeContent();
            profileContent.setContent(profiles);
            profileContent.setDisplayName("My Profile");
            profileContent.setPosition(Position.LEFT);
            profileContent.setResume(resume);

            resume.addResumeContents(profileContent);

            Content educations = new Content();
            educations.setDisplayOrder(2L);
            educations.setType(EDUCATION);
            educations.setName("MyEducation");
            educations.setCreateUserId(user.getId());
            educations.setUpdateUserId(user.getId());
            contentRepository.save(educations);

            Education education1 = new Education();
            education1.setContent(educations);
            education1.setContents("교육 이수");
            education1.setStartDt(LocalDateTime.of(2015, 3, 10, 0, 0));
            education1.setEndDt(LocalDateTime.of(2015, 4, 10, 0, 0));
            educations.addContentItem(education1);

            Education education2 = new Education();
            education2.setContent(educations);
            education2.setContents("대학 졸업");
            education1.setStartDt(LocalDateTime.of(2000, 3, 1, 0, 0));
            education1.setEndDt(LocalDateTime.of(2008, 2, 28, 0, 0));
            educations.addContentItem(education2);

            ResumeContent educationContent = new ResumeContent();
            educationContent.setContent(educations);
            educationContent.setDisplayName("My Education");
            educationContent.setPosition(Position.RIGHT);
            educationContent.setResume(resume);

            resume.addResumeContents(educationContent);
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
