package com.xinzhi.dao;
/**
 * kzx
 */
import java.util.List;

import com.xinzhi.pojo.PayOrder;
import com.xinzhi.pojo.User;

public interface IOrderFormDao {
	/**
	 * �����û���Ϣ
	 * @param p
	 * @return ��Ӱ������
	 */
	int deposit(PayOrder p);
	/**
	 * �鿴�û�������Ϣ
	 * @param index
	 * @param pagesize
	 * @return
	 */
	List<PayOrder> indent(int index, int pagesize,User u);
	/**
	 * ɾ��������Ϣ
	 * @return ��Ӱ������
	 */
	int deleteOrder();
	/**
	 * ��ȡ�û���������
	 * @return ����������
	 */
	int getCount();
}
