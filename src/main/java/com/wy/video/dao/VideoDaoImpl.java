package com.wy.video.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import com.wy.crawl.pojo.DownVideo;
import com.wy.utils.PageHibernateCallback;
import com.wy.video.pojo.Video;

@Repository
public class VideoDaoImpl implements IVideoDao  {

	private HibernateTemplate hibernateTemplate;
	LocalSessionFactoryBean sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	/**
	 * 查找最新num张的Video
	 */
	@Override
	public List<Video> findNew(int num) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Video.class);
		//按照上传时间进行倒序排序
		criteria.addOrder(Order.desc("uploadtime"));
		List<Video> list = (List<Video>) this.hibernateTemplate.findByCriteria(criteria, 0, num);
		return list;
	}

	/**
	 * 根据分类cid查询video总数
	 */
	@Override
	public int findCountByCid(Integer cid) {
		String hql = "select count(*) from Video v where v.category.cid=? ";
		List<Long> list = (List<Long>) this.hibernateTemplate.find(hql,cid);
		if(list !=null && list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 根据分类cid分页查询video
	 */
	@Override
	public List<Video> findByCid(Integer cid, int begin, int limit) {
		String hql = "select v from Video v join v.category c where c.cid=?";
//		String hql = "select v from Video v where v.cid=?";
		List<Video> list = this.hibernateTemplate.execute(new PageHibernateCallback<>(hql, new Object[]{cid}, begin, limit));
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	/**
	 * 根据分类id查询对应的热门视频
	 */
	@Override
	public List<Video> findHot(Integer cid) {
		String hql = "select v from Video v join v.category c where c.cid=? order by v.click desc";
		List<Video> list = this.hibernateTemplate.execute(new PageHibernateCallback<>(hql, new Object[]{cid}, 0, 2));
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	/**
	 * 根据视频vid查询video
	 */
	@Override
	public Video findByVid(Integer vid) {
		return this.hibernateTemplate.get(Video.class, vid);
	}
	
	/**
	 * 根据视频vid删除video
	 */
	@Override
	public void deleteByVid(Video video) {
		this.hibernateTemplate.delete(video);
	}

	/**
	 * 查询DownVideo视频总数
	 */
	@Override
	public int findDownVideo() {
		String hql = "select count(*) from DownVideo";
		List<Long> list = (List<Long>) this.hibernateTemplate.find(hql);
		if(list!=null && list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	
	/**
	 * 分页查询DownVideo
	 */
	@Override
	public List<DownVideo> findDownVideoByPage(int begin, int limit) {
		DetachedCriteria criteria = DetachedCriteria.forClass(DownVideo.class);
		List<DownVideo> list = (List<DownVideo>) this.hibernateTemplate.findByCriteria(criteria,begin,limit);
		return list;
	}
}
