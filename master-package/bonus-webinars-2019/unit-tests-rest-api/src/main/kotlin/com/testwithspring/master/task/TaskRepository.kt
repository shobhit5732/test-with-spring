package com.testwithspring.master.task

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * This repository isn't meant to be used in product.
 * This repository exists only because it allows us to
 * compile our example application and write unit tests
 * for it.
 */
@Repository
open class TaskRepository {

    /**
     * Finds the information of the specified task.
     * @param   id  The id of the requested task.
     * @return  This function returns {@code null}
     */
    @Transactional(readOnly = true)
    open fun findById(id: Long): Task? {
        return null
    }
}