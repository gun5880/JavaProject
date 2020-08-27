package kr.or.connect.cardmanager;

import java.util.List;

import kr.or.connect.cardmanager.dao.BusinessCardManagerDao;
import kr.or.connect.cardmanager.dto.BusinessCard;
import kr.or.connect.cardmanager.ui.CardManagerUI;

public class CardManager {

	public static void main(String[] args) {
		CardManagerUI cardManagerUI = CardManagerUI.getInstance();
		BusinessCardManagerDao businessCardManagerDao = new BusinessCardManagerDao();

		while_loop:
			while(true) {
				cardManagerUI.printMainMenu();
				int menuNumber = cardManagerUI.getMenuNumber();
				switch(menuNumber){
				case 1 :
					BusinessCard businessCard = cardManagerUI.inputBusinessCard();
					businessCardManagerDao.addBusinessCard(businessCard);
					break;
				case 2 :
					String searchKeyword = cardManagerUI.getSearchKeyword();
					List<BusinessCard> businessCardList = businessCardManagerDao.searchBusinessCard(searchKeyword);
					cardManagerUI.printBusinessCards(businessCardList);
					break;
				case 3 :
					cardManagerUI.printExitMessage();
					break while_loop;
				default:
					cardManagerUI.printErrorMessage();
					break;
				}
			}


	}

}
