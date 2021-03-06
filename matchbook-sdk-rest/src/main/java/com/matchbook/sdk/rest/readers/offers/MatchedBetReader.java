package com.matchbook.sdk.rest.readers.offers;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.dtos.offers.MatchedBet;
import com.matchbook.sdk.rest.dtos.offers.MatchedBetStatus;
import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.prices.OddsType;
import com.matchbook.sdk.rest.readers.ResponseReader;

public class MatchedBetReader extends ResponseReader<MatchedBet> {

    public MatchedBetReader() {
        super();
    }

    @Override
    protected MatchedBet readItem() throws MatchbookSDKParsingException {
        final MatchedBet matchedBet = new MatchedBet();
        while (!parser.isEndOfObject()) {
            parser.moveToNextValue();
            String fieldName = parser.getFieldName();
            if ("id".equals(fieldName)) {
                matchedBet.setId(parser.getLong());
            } else if ("offer-id".equals(fieldName)) {
                matchedBet.setOfferId(parser.getLong());
            } else if ("status".equals(fieldName)) {
                matchedBet.setStatus(parser.getEnum(MatchedBetStatus.class));
            } else if ("odds-type".equals(fieldName)) {
                matchedBet.setOddsType(parser.getEnum(OddsType.class));
            } else if ("odds".equals(fieldName)) {
                matchedBet.setOdds(parser.getDouble());
            } else if ("decimal-odds".equals(fieldName)) {
                matchedBet.setDecimalOdds(parser.getDouble());
            } else if ("currency".equals(fieldName)) {
                matchedBet.setCurrency(parser.getEnum(Currency.class));
            } else if ("stake".equals(fieldName)) {
                matchedBet.setStake(parser.getDecimal());
            } else if ("potential-profit".equals(fieldName)) {
                matchedBet.setPotentialProfit(parser.getDecimal());
            } else if ("potential-liability".equals(fieldName)) {
                matchedBet.setPotentialLiability(parser.getDecimal());
            } else if ("commission".equals(fieldName)) {
                matchedBet.setCommission(parser.getDecimal());
            } else if ("created-at".equals(fieldName)) {
                matchedBet.setCreatedAt(parser.getInstant());
            }
            parser.moveToNextToken();
        }
        return matchedBet;
    }

}
