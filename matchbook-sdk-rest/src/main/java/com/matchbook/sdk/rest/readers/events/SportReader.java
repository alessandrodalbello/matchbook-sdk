package com.matchbook.sdk.rest.readers.events;

import com.matchbook.sdk.core.exceptions.MatchbookSDKParsingException;
import com.matchbook.sdk.rest.dtos.events.Sport;
import com.matchbook.sdk.rest.readers.ResponseReader;

public class SportReader extends ResponseReader<Sport> {

    public SportReader() {
        super();
    }

    @Override
    protected Sport readItem() throws MatchbookSDKParsingException {
        final Sport sport = new Sport();
        while (!parser.isEndOfObject()) {
            parser.moveToNextValue();
            String fieldName = parser.getFieldName();
            if ("id".equals(fieldName)) {
                sport.setId(parser.getLong());
            } else if ("name".equals(fieldName)) {
                sport.setName(parser.getString());
            }
            parser.moveToNextToken();
        }
        return sport;
    }

}
