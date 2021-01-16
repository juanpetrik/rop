package com.petrik.rop;

import com.petrik.rop.business.PeopleBusinessTest;
import com.petrik.rop.exception.ExceptionTest;
import com.petrik.rop.service.PeopleServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = {
        PeopleServiceTest.class,
        PeopleBusinessTest.class,
        ExceptionTest.class
})
public class RunSuite {
}
