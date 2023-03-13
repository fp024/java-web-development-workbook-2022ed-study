package org.fp024.springex.sample;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

@ToString
public class SampleService {

  @Autowired private SampleDAO sampleDAO;
}
