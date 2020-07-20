package com.dm.validate.group;

import javax.validation.GroupSequence;

@GroupSequence({DefaultValidated.class, FirstValidated.class, SecondValidated.class, ThirdValidated.class})
public interface ValidatedSequence {
}
