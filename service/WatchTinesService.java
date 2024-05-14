package movieSell.service;

import movieSell.bean.po.WatchTimes;
import org.springframework.stereotype.Service;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
public interface WatchTinesService {
    WatchTimes selectOne(String watchTimeId);
}
