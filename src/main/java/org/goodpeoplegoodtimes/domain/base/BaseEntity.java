package org.goodpeoplegoodtimes.domain.base;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * BaseEntity는 엔터티의 공통 요소인 생성 시간과 수정 시간을 관리
 * 이 클래스를 상속받는 엔터티는 자동으로 생성 시간과 수정 시간을 가지게 됌.
 *
 * @MappedSuperclass: JPA가 해당 클래스를 엔터티로 직접 사용하지 않고 상속받은 엔터티가 공통 매핑 정보를 사용하도록 함.
 * @EntityListeners(AuditingEntityListener.class): 엔터티가 생성되거나 수정될 때 콜백 이벤트를 받기 위한 리스너 지정.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    /**
     * @CreatedDate: 엔터티가 처음 저장될 때 현재 시간을 자동으로 할당한다.
     * updatable = false: 한 번 할당된 후 변경되지 않도록 설정
     * nullable = false: 항상 값이 존재해야 하므로 null을 허용하지 않음
     */
    @CreatedDate
    private LocalDateTime createdAt;

    /**
     * @LastModifiedDate: 엔터티가 수정될 때마다 현재 시간으로 자동으로 업데이트
     * nullable = false: 항상 값이 존재해야 하므로 null을 허용하지 않음.
     */
    @LastModifiedDate
    private LocalDateTime modifiedAt;

}

