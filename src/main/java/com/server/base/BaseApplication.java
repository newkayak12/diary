package com.server.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);

        log.error("-----------------------------------------------------------------------------");
        log.error(" ⊂_ヽ ");
        log.error("　 ＼＼ Λ＿Λ ");
        log.error("　　 ＼( ‘ㅅ’ ) 두둠칫 ");
        log.error("　　　 >　⌒ヽ ");
        log.error("　　　/ 　 へ＼ ");
        log.error("　　 /　　/　＼＼ ");
        log.error("　　 ﾚ　ノ　　 ヽ_つ ");
        log.error("　　/　/두둠칫 ");
        log.error("　 /　/| ");
        log.error("(　(ヽ ");
        log.error("　|　|、＼ ");
        log.error("　| 丿 ＼ ⌒) ");
        log.error("　| |　　) / ");
        log.error("`ノ )　　Lﾉ");
        log.error("-----------------------------------------------------------------------------");

    }
}
