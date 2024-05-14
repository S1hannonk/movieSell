package movieSell.service;

import movieSell.bean.bo.SiteChooseBo;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
public interface SiteService {
    boolean choose(SiteChooseBo siteChooseBo,String authorization);
}
