package Controller;

import Controller.game.Card;
import Controller.game.SpecialCard;
import Model.CardModel;


import java.util.ArrayList;

public class CardToCardConvertor {
    public static CardModel convertCardToCardModel(Card card) {
        return new CardModel(card.getName(), card.getDamage(), card.getWidth(), card.getDefense(), 1, card.getShopCost());
    }

    public static Card convertCardModelToCard(CardModel cardModel) {
        return new Card(cardModel.getName(), 1, cardModel.getDamage(), cardModel.getDefence(), cardModel.getUpgradeCost(), cardModel.getDuration());
    }

    public static ArrayList<Card> convertCardModelListToCardList(ArrayList<CardModel> cardModels) {
        ArrayList<Card> cards = new ArrayList<>();
        for (CardModel cardModel : cardModels) {
            if (cardModel.getName().contains("support")) {
                if (cardModel.getName().contains("Bomb")) {
                    cards.add(new SpecialCard(cardModel.getName(), "bomb", 1, cardModel.getDamage(), cardModel.getDefence(), SpecialCard.SpecialCardType.BOMB));
                    continue;
                }
                if (cardModel.getName().contains("Heal")) {
                    cards.add(new SpecialCard(cardModel.getName(), "heal", 1, cardModel.getDamage(), cardModel.getDefence(), SpecialCard.SpecialCardType.HEAL));
                    continue;
                }
                if (cardModel.getName().contains("Shield")) {
                    cards.add(new SpecialCard(cardModel.getName(), "shield", 1, cardModel.getDamage(), cardModel.getDefence(), SpecialCard.SpecialCardType.SHIELD));
                    continue;
                }
                if (cardModel.getName().contains("RoundSetBacker")) {
                    cards.add(new SpecialCard(cardModel.getName(), "round setbacker", 1, cardModel.getDamage(), cardModel.getDefence(), SpecialCard.SpecialCardType.ROUNDSETBACKER));
                    continue;
                }
                if (cardModel.getName().contains("RoundAdvancer")) {
                    cards.add(new SpecialCard(cardModel.getName(), "round advancer", 1, cardModel.getDamage(), cardModel.getDefence(), SpecialCard.SpecialCardType.ROUNDADVANCER));
                    continue;
                }
                if (cardModel.getName().contains("CardDeleter")) {
                    cards.add(new SpecialCard(cardModel.getName(), "card deleter", 1, cardModel.getDamage(), cardModel.getDefence(), SpecialCard.SpecialCardType.CARDDELETER));
                    continue;
                }
                if (cardModel.getName().contains("CardStealer")) {
                    cards.add(new SpecialCard(cardModel.getName(), "card stealer", 1, cardModel.getDamage(), cardModel.getDefence(), SpecialCard.SpecialCardType.CARDSTEALER));
                    continue;
                }
                if (cardModel.getName().contains("Damager")) {
                    cards.add(new SpecialCard(cardModel.getName(), "damager", 1, cardModel.getDamage(), cardModel.getDefence(), SpecialCard.SpecialCardType.DAMAGER));
                    continue;
                }
                if (cardModel.getName().contains("hider")) {
                    cards.add(new SpecialCard(cardModel.getName(), "hider", 1, cardModel.getDamage(), cardModel.getDefence(), SpecialCard.SpecialCardType.HIDER));
                    continue;
                }
                if (cardModel.getName().contains("poisoner")) {
                    cards.add(new SpecialCard(cardModel.getName(), "poisoner", 1, cardModel.getDamage(), cardModel.getDefence(), SpecialCard.SpecialCardType.POISONER));
                    continue;
                }
                if (cardModel.getName().contains("blocker")) {
                    cards.add(new SpecialCard(cardModel.getName(), "poison", 1, cardModel.getDamage(), cardModel.getDefence(), SpecialCard.SpecialCardType.BLOCKER));
                    continue;
                }
                if (cardModel.getName().contains("changeBlocker")) {
                    cards.add(new SpecialCard(cardModel.getName(), "changeBlocker", 1, cardModel.getDamage(), cardModel.getDefence(), SpecialCard.SpecialCardType.CHANGEBLOCKPOSITION));
                    continue;
                }

            }
            cards.add(convertCardModelToCard(cardModel));
        }
        return cards;
    }
}
