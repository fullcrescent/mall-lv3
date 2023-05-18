# 

## 1. Eventstorming Model
![그림](https://github.com/fullcrescent/mall-lv3/assets/53729857/1d2c8acd-c529-4c6b-ae70-5b570302c052)

## 2. Saga (Pub / Sub) 확인 (클러스터에 Kafka 설치 후)
### helm 설치
1. curl https://raw.githubusercontent.com/helm/helm/master/scripts/get-helm-3 > get_helm.sh
1. chmod 700 get_helm.sh
1. ./get_helm.sh

### helm 레포지토리에 kafka 설치
1. helm repo add bitnami https://charts.bitnami.com/bitnami
1. helm repo update
1. helm install my-kafka bitnami/kafka

### 카프카 클라이언트 pod로 실행 -> pod 접속 후 Saga 확인
1. kubectl run my-kafka-client --restart='Never' --image docker.io/bitnami/kafka:2.8.0-debian-10-r0 --command -- sleep infinity
1. kubectl exec --tty -i my-kafka-client -- bash
1. kafka-console-consumer.sh --bootstrap-server my-kafka:9092 --topic malllv --from-beginning
![image](https://github.com/fullcrescent/mall-lv3/assets/53729857/fc92a68b-995e-41e7-9be7-622c9cb9a508)

## 3. Service Router 설치
1. kubectl apply -f /workspace/mall-lv3/kubernetes/template.yml
1. kubectl apply -f /workspace/mall-lv3/gateway/kubernetes/deployment.yml
1. kubectl apply -f /workspace/mall-lv3/gateway/kubernetes/service.yaml
![image](https://github.com/fullcrescent/mall-lv3/assets/53729857/56f08333-fc1b-479c-a40b-8ca0ff960681)

## 4. Zero downtime Deployment
- siege pod 실행 및 접속
```
kubectl apply -f - <<EOF
apiVersion: v1
kind: Pod
metadata:
  name: siege
spec:
  containers:
  - name: siege
    image: apexacme/siege-nginx
EOF

kubectl exec -it siege -- /bin/bash
```
![image](https://github.com/fullcrescent/mall-lv3/assets/53729857/f6c84b50-b831-4ee0-98db-e930935e2218)

무중단 배포 설정 추가X
![image](https://github.com/fullcrescent/mall-lv3/assets/53729857/e3924d9a-65dc-419b-86a6-964285eaabc7)

무중단 배포 설정 추가O
![image](https://github.com/fullcrescent/mall-lv3/assets/53729857/50f9a616-0803-42a7-8ad4-b461ffb4cc57)
